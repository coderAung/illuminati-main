package edu.ucsy.social.data;

import java.util.List;

public interface Model<T> {

	T save(T t);
	
	T findOne(long id);
	
	T findOne(long id, String ...cols);
	
	List<T> getAll();
	
	List<T> get(long limit);
	
	T update(T t);
	
	T fullUpdate(T t);
	
	boolean delete(long id);
    
	default Relational getRelational() {
		if(this instanceof Relational model) {
			return model;
		}
		return null;
	}
}