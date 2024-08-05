package edu.ucsy.social.data.db;

import java.sql.Connection;

public interface DatabaseConnector {

	public Connection getConnection();

}