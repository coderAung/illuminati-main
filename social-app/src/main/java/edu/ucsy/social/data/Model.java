package edu.ucsy.social.data;

import java.sql.ResultSet;
import java.util.List;

public interface Model<T> {

	T save(T t);
	
	T findOne(long id);
	
	ResultSet findOne(long id, String ...cols);
	
	List<T> getAll();
	
	List<T> get(long limit);
	
	T update(T t);
	
	T fullUpdate(T t);
	
	boolean delete(long id);
    
	@SuppressWarnings("unchecked")
	default <R extends Relational> R getRelational(Class<R> r) {

		if(this instanceof Relational model) {
			return (R) model;
		}

		return null;
	}
}