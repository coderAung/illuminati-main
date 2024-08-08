package edu.ucsy.social.data;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Relational {

	<T> List<T> getMany(Class<T> e, long id);
	
	<T> T getOne(Class<T> e, long id);
	
	<T> long count(Class<T> e, long id);
	
	<T> Map<Integer, List<T>> getMany(Class<T> e, Set<Integer> idSet);
}
