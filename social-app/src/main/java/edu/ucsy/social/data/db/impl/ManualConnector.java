package edu.ucsy.social.data.db.impl;

import java.sql.Connection;
import java.sql.SQLException;

import edu.ucsy.social.data.db.DatabaseConnector;

public class ManualConnector implements DatabaseConnector {
	
	@Override
    public Connection getConnection() throws SQLException {
        return null;
    }

}