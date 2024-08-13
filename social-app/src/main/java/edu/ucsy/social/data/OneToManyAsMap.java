package edu.ucsy.social.data;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OneToManyAsMap extends Relational {

	<T> Map<Integer, List<T>> getMany(Class<T> e, Set<Integer> idSet);

}
