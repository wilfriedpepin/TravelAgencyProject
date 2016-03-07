package fr.imie.travelagencyproject.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static final String URL = "jdbc:mysql://localhost:3306/javaclass";
	private static final String USER = "wilfried";
	private static final String PASSWORD = "wilfried";
	private static Connection connection;
	
	public static Connection getConnection(){
		if(connection == null){
			try{
				connection = DriverManager.getConnection(URL,USER,PASSWORD);
				connection.setAutoCommit(false);
			}catch(SQLException e){
				throw new RuntimeException("Unable to create connection with "
						+ "database(url: "+ ""+ URL + ",user: " + USER,e);
						
			}
		}
		return connection;
	}
	public static void close(){
	try{
		connection.close();
	}catch(SQLException e){
		throw new RuntimeException("Enable to close connection",e);
	}
}
}

