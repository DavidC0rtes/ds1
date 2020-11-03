package user.register;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.JDBCConnection;

/**
 * Sirve como modelo para los usuarios del sistema.
 * Capa de acceso a datos.
 */
public class User {
	private int cedula;
	
	private String rol;
	private String primerNombre;
	private String segundoNombre;
	private String email;
	private String password;

	
	/**
	 * Se asegura de que no exista una fila en la tabla de usuarios
	 * con la misma cedula.
	 * 
	 * @param String cedula
	 * @return false sí el usuario no existe | true en caso contrario.
	 * @throws SQLException
	 */
	public boolean userExists(int cedula) throws SQLException {
		String query = 
				"SELECT * "
				+ "FROM usuarios "
				+ "WHERE num_documento = "
				+ cedula;
		
		if (!JDBCConnection.getRecords(query).isBeforeFirst()) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Verifica que el rol asignado al usuario sea válido.
	 * @param idRol
	 * @return true | false
	 * @throws SQLException
	 */
	public boolean roleIsValid(String rol) throws SQLException {
		String query =
				"SELECT * "
				+ "FROM roles"
				+ "WHERE nombre ="
				+ rol;
		
		if (JDBCConnection.getRecords(query).isBeforeFirst()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Escribe el objeto usuario a la base de datos.
	 * Es decir, crea el usuario en la bd.
	 * 
	 * @param User usuario, instancia de la clase User
	 * @throws SQLException 
	 */
	public void createUser() throws SQLException {
		/**
		 * Antes de hacer persistentes los datos, hay que hacer
		 * ciertas validaciones.
		 */
		if (!userExists(cedula) && roleIsValid(rol) ) {
			String createUserSQL = 
					"INSERT INTO usuarios"
					+ "VALUES (?,?,?,?,?,?,?)";
			
			String[] userParams = {
						Integer.toString(cedula),
						primerNombre,
						segundoNombre,
						password,
						email,
						Integer.toString(1), // Campo activo
						Integer.toString(getIdRol(rol))
				};
			
			JDBCConnection.updateRecord(createUserSQL, userParams);
		}
		
	}
	
	private int getIdRol(String rol) throws SQLException {
		ResultSet rs = JDBCConnection.getRecords("select id from rol where id="+rol);
		return rs.getInt(1);
	}
	
	/** setters **/
	public void setCedula(int cedula) {this.cedula = cedula;}
	
	public void setEmail(String email) {this.email = email;}
	
	public void setPrimerNombre(String nombre) {this.primerNombre = nombre;}
	
	public void setSegundoNombre(String nombre) {this.segundoNombre = nombre;}
	
	public void setPassword(String password) {this.password = password;}
	
	public void setRol(String Rol) {this.rol = Rol;}
	
	
	/* getters */
	//public int getID() {return idRol;}
	
	public int getCC() {return cedula;}
	
	//public int getIdRol() {return idRol;}
	
	public String getEmail() {return email;}
	
	public String getPassword() {return password;}
}
