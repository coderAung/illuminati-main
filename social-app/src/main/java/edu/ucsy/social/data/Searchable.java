package edu.ucsy.social.data;

import java.sql.Connection;
import java.util.List;

import edu.ucsy.social.data.criteria.Criteria;

public interface Searchable<T> {

	T searchOne(Criteria c);
	
	List<T> search(Criteria c);
		
	void setConnection(Connection connection);

}
