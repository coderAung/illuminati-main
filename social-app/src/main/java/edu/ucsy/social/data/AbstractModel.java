package edu.ucsy.social.data;

import edu.ucsy.social.data.db.DatabaseConnector;

public abstract class AbstractModel<T> implements Model<T> {

    protected DatabaseConnector connector;

    public AbstractModel(DatabaseConnector connector) {
    	this.connector = connector;
	}
    
}