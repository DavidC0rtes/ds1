package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.util.Properties;

public class JDBCConnection {
	private String dbPassword;
	private String dbName;
	private String dbUser;
	private String dbHost;
	private String dbUrl;
	private static Connection conn;
	
	Properties props = new Properties();
	
	public JDBCConnection() {
		try {
			FileReader reader = new FileReader("src/db/db.properties");
			props.load(reader);
			
			dbHost = props.getProperty("DB_HOST");
			dbUser = props.getProperty("DB_USER");
			dbName = props.getProperty("DB_NAME");
			dbPassword = props.getProperty("USER_PASSWORD");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		dbUrl = String.format("jdbc:postgresql://%s:5432/%s?user=%s&password=%s", dbHost, dbName, dbUser, dbPassword);
		
		try {
			conn = DriverManager.getConnection(dbUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return conn;
	}
	
	
	public ResultSet getRecords(String query) {
		ResultSet rs = null;
		try (Statement stmt = conn.createStatement()){
			rs = stmt.executeQuery(query);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * Retorna el resultado de una consulta a la bd.
	 * 
	 * @param Connection conn, objeto que contiene la conexión con la bd.
	 * @param String query
	 * 
	 * @return ResultSet | Null
	 * @throws SQLException
	 */
	public ResultSet getRecords(String query, String[] params) {
		ResultSet rs = null;
		try {
			PreparedStatement recordStatement = conn.prepareStatement(query);
			for (int i=0; i < params.length; i++) {
				recordStatement.setString(i+1, params[i]);
			}
			rs = recordStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * Modifica/añade/elimina un registro de la base de datos.
	 * 
	 * @param String query
	 * @param String[] params, arreglo de valores que se van a utilizar en la query
	 * 
	 * @return int número de filas afectadas ó 0.
	 */
	public int updateRecord(String query, String[] params) {
		int rows = 0;
		try {
			PreparedStatement updateStatement = conn.prepareStatement(query);
			for( int i = 0; i < params.length; i++) {
				
				if (params[i].chars().allMatch( Character::isDigit )) {	
					updateStatement.setInt(i+1, Integer.parseInt(params[i]));
				}
				else {
					updateStatement.setString(i+1, params[i]);
				}
			}
			rows = updateStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
}
