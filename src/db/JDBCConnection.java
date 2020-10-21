package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
			props.load(new FileInputStream(new File("db.properties")));
			
			dbHost = props.getProperty("DB_HOST");
			dbUser = props.getProperty("DB_USER");
			dbName = props.getProperty("BD");
			dbPassword = props.getProperty("USER_PASSWORD");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		dbUrl = String.format("jdbc:postgresql://%s:5243/%s?user=%s&password=%s", dbHost, dbName, dbUser, dbPassword);
		
		try {
			getConnection(dbUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static Connection getConnection(String url) throws SQLException {
		return DriverManager.getConnection(url);
	}
}
