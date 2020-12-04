package sige.db;

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
			InputStream inputStream = getClass().getResourceAsStream("/db.properties");

			props.load(inputStream);
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

	 * 
	 * @return ResultSet | Null
	 * @throws SQLException
	 */
	public ResultSet getRecords(String query, String[] params) {
		ResultSet rs = null;
		try {
			PreparedStatement recordStatement = conn.prepareStatement(query);
			for (int i=0; i < params.length; i++) {
				if (params[i].chars().allMatch( Character::isDigit )) {	
					recordStatement.setInt(i+1, Integer.parseInt(params[i]));
				
				} else  if (params[i].equals("true") || params[i].equals("t")) {
                	recordStatement.setBoolean(i+1, true);
                
				} else if(params[i].equals("false") || params[i].equals("f")){
                   	recordStatement.setBoolean(i+1, false);
                   	
				} else {
					recordStatement.setString(i+1, params[i]);
				}
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
				
				} else  if (params[i].equals("true") || params[i].equals("t")) {
                	updateStatement.setBoolean(i+1, true);
                
				} else if(params[i].equals("false") || params[i].equals("f")){
                   	updateStatement.setBoolean(i+1, false);
                   	
				} else if(params[i].matches("^[0-9]+\\.*[0-9]+$")) {
					updateStatement.setFloat(i+1, Float.parseFloat(params[i]));
				} else {
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
