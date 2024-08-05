package edu.ucsy.social.data;

import java.util.List;

import edu.ucsy.social.data.relation.RelationalModel;

public interface Model<T> {

	T save(T t);
	
	T findOne(long id);
	
	List<T> getAll();
	
	List<T> get(long limit);
	
	T update(T t);
	
	T fullUpdate(T t);
	
	boolean delete(long id);
	
	long count();
    
	default RelationalModel getRelationalModel() {
		if(this instanceof RelationalModel model) {
			return model;
		}
		return null;
	}

}