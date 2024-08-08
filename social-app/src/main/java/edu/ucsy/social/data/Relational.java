package edu.ucsy.social.data;

import java.util.List;

public interface Relational {

	<T> List<T> getMany(Class<T> e);
	
	<T> T getOne(Class<T> e);
	
	<T> long count(Class<T> e, long id);
}
