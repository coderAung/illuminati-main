package edu.ucsy.social.data.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnector {

	public Connection getConnection() throws SQLException;

}