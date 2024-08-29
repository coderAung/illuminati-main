package edu.ucsy.social.data;

import java.sql.Connection;

public interface Service {
	
	void initConnection(Connection connection);
	
	void destroyConnection();
}
