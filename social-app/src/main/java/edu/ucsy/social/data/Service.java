package edu.ucsy.social.data;

import edu.ucsy.social.data.db.DatabaseConnector;

public abstract class Service {
	
	protected DatabaseConnector connector;
	
	public Service(DatabaseConnector connector) {
		this.connector = connector;
	}
	
	protected abstract void initConnection();
	
	protected abstract void destroyConnection();
}
