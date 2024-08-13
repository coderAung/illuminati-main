package edu.ucsy.social.data;

import java.util.List;

public interface BatchModel<T> {
	
	List<T> saveBatch(List<T> batch);
	
	boolean deleteBatch(List<Long> idList);
}
