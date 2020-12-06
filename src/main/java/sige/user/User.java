package sige.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import sige.db.JDBCConnection;

/**
 * Esta clase sirve como "libreria" para realizar
 * actividades del usuario que necesiten interacción
 * con la BD y para recolectar información del usuario
 * que se necesite para otras vistas/interfaces, etc.
 * 
 * - Logearse al sistema.
 * - Cerrar sesión.
 * - Averiguar que rol tiene el usuario.
 * - Averiguar el nombre del usuario.
 * - Demás actividades que consideren necesarias.
 * @author santiago
 *
 */
public class User {
	private JDBCConnection DB;
	private int idRol, idUser;
	private String email, primerNombre, primerApellido;

	public User(String cc, String password) {
		DB = new JDBCConnection();
	}
	
	/**
	 * Consulta la BD para saber si existe una combinación
	 * para la cédula y la contraseña dadas.
	 * @param cc
	 * @param password
	 */
	public boolean attemptLogin(String cc, String password) {
		String query = 
				"SELECT primer_nombre, primer_apellido, email, id_rol, id "
				+ "FROM usuarios "
				+ "WHERE num_documento=? "
				+ "AND password=?"
				+ "AND activo=?";
		
		String[] params = {cc, password, "true"};
		ResultSet rs = DB.getRecords(query, params);
		
		try {
			if (!rs.isBeforeFirst() ) {
				return false;
			}
			else {
				rs.next();
				this.primerNombre = rs.getString(1);
				this.primerApellido = rs.getString(2);
				this.email = rs.getString(3);
				this.idRol = rs.getInt(4);
				this.idUser = rs.getInt(5);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * getters
	 */
	public String getStringRol(){
		if (idRol == 1 ) {	return "Administrador"; }
		if (idRol == 2 ) {	return "Gerente"; }
		if (idRol == 3 ) {	return "Operador"; }

		return "Tester";
	}

	public int getIdRol() {return idRol;}

	public String getEmail() {return email;}

	public String getPrimer_nombre() {return primerNombre;	}
	
	public String getPrimer_apellido() {return primerApellido;}
	
	public int getUserID() {return idUser;}
}
