package edu.ucsy.social.data;

import java.sql.Connection;

import edu.ucsy.social.data.db.DatabaseConnector;

public abstract class Service {
	
	protected DatabaseConnector connector;
	
	public Service(DatabaseConnector connector) {
		this.connector = connector;
	}
	
	protected abstract void initConnection(Connection connection);
	
	protected abstract void destroyConnection();
}
