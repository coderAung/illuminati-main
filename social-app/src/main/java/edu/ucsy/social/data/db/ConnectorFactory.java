package edu.ucsy.social.data.db;

import javax.sql.DataSource;

import edu.ucsy.social.data.db.impl.DataSourceConnector;

public abstract class ConnectorFactory {
	
	public static DatabaseConnector getConnector() {
		return null;
	}

	public static DatabaseConnector getConnector(DataSource dataSource) {
		return new DataSourceConnector(dataSource);
	}
}
