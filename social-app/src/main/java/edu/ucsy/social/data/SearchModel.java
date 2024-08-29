package edu.ucsy.social.data;

import java.sql.Connection;

public abstract class SearchModel<T> implements Searchable<T> {

	protected Connection connection;
	
	public SearchModel(Connection connection) {
		this.connection = connection;
	}
}
