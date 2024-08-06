package edu.ucsy.social.data;

import edu.ucsy.social.data.db.DatabaseConnector;

public abstract class SearchModel<T> implements Searchable<T> {

	protected DatabaseConnector connector;
	
	public SearchModel(DatabaseConnector connector) {
		this.connector = connector;
	}
}
