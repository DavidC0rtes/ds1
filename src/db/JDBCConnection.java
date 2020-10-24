package db;

import java.sql.Connection;
import java.sql.DriverManager;
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
			getConnection(dbUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retorna la conexión con la base de datos.
	 * @param String url, url que debe utilizar el driver para conectarse.
	 * @return Connection
	 * @throws SQLException
	 */
	private static Connection getConnection(String url) throws SQLException {
		return DriverManager.getConnection(url);
	}
	
	/**
	 * Retorna el resultado de una consulta a la bd.
	 * @param Connection conn, objeto que contiene la conexión con la bd.
	 * @param String query
	 * @return ResultSet | Null
	 * @throws SQLException
	 */
	public static ResultSet getRecords(Connection conn, String query) {
		ResultSet rs = null;
		try {
			Statement recordStatement = conn.createStatement();
			rs = recordStatement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
}
