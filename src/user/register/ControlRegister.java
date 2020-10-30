package user.register;

import java.sql.SQLException;

import db.JDBCConnection;

public class ControlRegister {
	private User usuario;
	
	public ControlRegister() {
		usuario = new User();
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
	public boolean roleIsValid(int idRol) throws SQLException {
		String query =
				"SELECT * "
				+ "FROM roles"
				+ "WHERE id ="
				+ idRol;
		
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
	public void createUser(User usuario) throws SQLException {
		/**
		 * Antes de hacer persistentes los datos, hay que hacer
		 * ciertas validaciones.
		 */
		if (!userExists(usuario.getCC()) && roleIsValid(usuario.getIdRol()) ) {
			
		}
		
	}
}
