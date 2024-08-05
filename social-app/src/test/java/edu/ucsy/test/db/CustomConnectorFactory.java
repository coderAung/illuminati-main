package edu.ucsy.test.db;

import java.sql.DriverManager;

import edu.ucsy.social.data.db.DatabaseConnector;

public class CustomConnectorFactory {

	private static final String URL = "jdbc:mysql://localhost:3306/social_db";
	private static final String USER = "root";
	
	public static DatabaseConnector getConnectorWithAdmin() {
		return () ->DriverManager.getConnection(URL, USER, "admin");
	}

	public static DatabaseConnector getConnectorWithoutPassword() {
		return () ->DriverManager.getConnection(URL, USER, "");
	}

	public static DatabaseConnector getConnectorWithRoot() {
		return () ->DriverManager.getConnection(URL, USER, "root");
	}
}
