package edu.ucsy.social.data;

import java.util.List;

public interface OneToMany extends Relational {
	
	<T> List<T> getMany(Class<T> e, long id);
	
	<T> List<T> getMany(Class<T> e, long id, long limit);
	
	<T> boolean deleteMany(Class<T> e, long id);

	<T> long countMany(Class<T> e, long userId);
}
