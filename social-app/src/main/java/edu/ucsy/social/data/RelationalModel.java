package edu.ucsy.social.data;

public interface RelationalModel extends OneToMany, OneToOne {
		
	<T> long count(Class<T> e, long id);
}
