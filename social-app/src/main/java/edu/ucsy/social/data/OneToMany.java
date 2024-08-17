package edu.ucsy.social.data;

import java.util.List;

public interface OneToMany extends Relational {
	
	<T> List<T> getMany(Class<T> e, long id);
	
	<T> boolean deleteMany(Class<T> e, long id);
}
