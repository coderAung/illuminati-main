package edu.ucsy.social.data;

import edu.ucsy.social.data.criteria.Criteria;

public interface Countable {
	long count(Criteria criteria);
	
	static <T> Countable getCountable(T t) {
		
		if(t instanceof Countable) {
			return (Countable) t;
		} else {
			throw new IllegalArgumentException("%s is not a Countable instance".formatted(t.getClass().getSimpleName()));
		}
		
	}
}
