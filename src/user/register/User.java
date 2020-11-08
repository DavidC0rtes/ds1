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
	private String primerApellido;
	private String segundoApellido;
	private String email;
	private String password;
	private JDBCConnection conn;
	
	public User() {
		conn = new JDBCConnection();
	}

	
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
		
		if (!conn.getRecords(query).isBeforeFirst()) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Verifica que el rol asignado al usuario sea válido.
	 * @param String rol
	 * @return true | false
	 * @throws SQLException
	 */
	public boolean roleIsValid(String rol) throws SQLException {
		String query ="SELECT * FROM roles WHERE nombre_rol = ?";
		String[] params = {rol};
		
		if (conn.getRecords(query, params).isBeforeFirst()) {
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
	public int createUser() throws SQLException {
		/**
		 * Antes de hacer persistentes los datos, hay que hacer
		 * ciertas validaciones.
		 */
		if (!userExists(cedula) && roleIsValid(rol) ) {
			String createUserSQL = 
					"INSERT "
					+ "INTO usuarios(num_documento,primer_nombre,primer_apellido,password,email,id_rol) "
					+ "VALUES(?,?,?,?,?,?)";
			
			String[] userParams = {
						Integer.toString(cedula),
						primerNombre,
						primerApellido,
						password,
						email,
						Integer.toString(getIdRol(rol))
				};
			return conn.updateRecord(createUserSQL, userParams);
		}
		return -1;
	}
	
	private int getIdRol(String rol) throws SQLException {
		ResultSet rs = conn.getRecords("select id from roles where nombre_rol='"+rol+"'");
		if(rs.next()) {
			return rs.getInt("id");
		}
		return 0;
	}
	
	/** setters **/
	public void setCedula(int cedula) {this.cedula = cedula;}
	
	public void setEmail(String email) {this.email = email;}
	
	public void setPrimerNombre(String nombre) {this.primerNombre = nombre;}
	
	public void setSegundoNombre(String nombre) {this.segundoNombre = nombre;}
	
	public void setPrimerApellido(String apellido) {this.primerApellido = apellido;}
	
	public void setPassword(String password) {this.password = password;}
	
	public void setRol(String Rol) {this.rol = Rol;}
	
	
	/* getters */
	//public int getID() {return idRol;}
	
	public int getCC() {return cedula;}
	
	//public int getIdRol() {return idRol;}
	
	public String getEmail() {return email;}
	
	public String getPassword() {return password;}
}
