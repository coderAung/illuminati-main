package edu.ucsy.social.data;

import edu.ucsy.social.data.relation.RelationalModel;

public interface Model<T> {

	T save(T t);
	
	T findOne(long id);
	
	T update(T t);
	
	T fullUpdate(T t);
	
	boolean delete(long id);
    
	default RelationalModel getRelationalModel() {
		if(this instanceof RelationalModel model) {
			return model;
		}
		return null;
	}

}