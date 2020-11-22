package user;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.JDBCConnection;

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
	private int idRol;
	private String email, primerNombre, primerApellido, cc;

	public User(String cc, String password) {
		DB = new JDBCConnection();
		if (attemptLogin(cc, password)) {
			
			this.cc = cc;
			
			ResultSet rs = getAll();

			try {
				this.primerNombre = rs.getString(1);
				this.primerApellido = rs.getString(2);
				this.email = rs.getString(3);
				this.idRol = rs.getInt(4);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Consulta la BD para saber si existe una combinación
	 * para la cédula y la contraseña dadas.
	 * @param cc
	 * @param password
	 */
	private boolean attemptLogin(String cc, String password) {
		String query = 
				"SELECT id "
				+ "FROM usuarios "
				+ "WHERE num_documento=? "
				+ "AND password=?";
		
		String[] params = {cc, password};
		ResultSet rs = DB.getRecords(query, params);
		
		try {
			if (!rs.isBeforeFirst() ) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	private ResultSet getAll() {
		String sql = 
				"select primer_nombre, primer_apellido, email, id_rol"
				+ "from usuarios"
				+ "where num_documento = ?";
		
		String[] params = {cc};
		return DB.getRecords(sql, params);
	}
	
	/**
	 * Setters y getters
	 */
	public int getIdRol() {return idRol;}

	public String getEmail() {return email;}

	public String getPrimer_nombre() {return primerNombre;	}
	
	public String getPrimer_apellido() {return primerApellido;}
}
