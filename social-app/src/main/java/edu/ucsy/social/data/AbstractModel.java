package edu.ucsy.social.data;

import java.sql.Connection;

public abstract class AbstractModel<T> implements Model<T> {

    protected Connection connection;
    
    @Override
    public void setConnection(Connection connection) {
		this.connection = connection;
	}
}