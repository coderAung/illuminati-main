package edu.ucsy.social.data;

import edu.ucsy.social.data.criteria.Criteria;

public interface Deletable {
	boolean delete(Criteria criteria, String ...tableName);
	
	static <T> Deletable getDeletable(T t) {
		if(t instanceof Deletable deletable) {
			return deletable;
		}
		throw new IllegalArgumentException("%s is not a Deletable".formatted(t.getClass().getSimpleName()));
	}
}
