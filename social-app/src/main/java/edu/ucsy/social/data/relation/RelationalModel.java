package edu.ucsy.social.data.relation;

import java.util.List;

public interface RelationalModel {

	List<?> getManyBy(long id);
	
	long countBy(long id);
}
