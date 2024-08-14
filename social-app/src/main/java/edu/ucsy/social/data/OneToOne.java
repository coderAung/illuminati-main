package edu.ucsy.social.data;

public interface OneToOne extends Relational {

	<T> T getOne(Class<T> e, long id);

	<T> boolean deleteOne(Class<T> e, long id);
}
