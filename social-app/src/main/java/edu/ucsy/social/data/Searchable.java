package edu.ucsy.social.data;

import java.util.List;

import edu.ucsy.social.data.criteria.Criteria;

public interface Searchable<T> {

	T searchOne(long id);
	
	T searchOne(Criteria c);
	
	List<T> search(long limit);

	List<T> search(Criteria c);
	
	List<T> search(Criteria c, long limit);
}
