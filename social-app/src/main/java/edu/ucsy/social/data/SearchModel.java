package edu.ucsy.social.data;

import java.sql.Connection;
import java.util.List;

import edu.ucsy.social.data.criteria.Criteria;

public abstract class SearchModel<T> implements Searchable<T> {

	protected Connection connection;

	public abstract List<T> search(Criteria c, long limit);

	public abstract List<T> searchLatest(long limit);
	
	public abstract List<T> searchLatest(Criteria c, long limit);

}
