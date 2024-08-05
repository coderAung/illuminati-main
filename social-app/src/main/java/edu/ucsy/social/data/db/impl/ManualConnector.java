package edu.ucsy.social.data.db.impl;

import java.sql.Connection;

import edu.ucsy.social.data.db.DatabaseConnector;

public class ManualConnector implements DatabaseConnector {
	
	@Override
    public Connection getConnection() {
        return null;
    }

}