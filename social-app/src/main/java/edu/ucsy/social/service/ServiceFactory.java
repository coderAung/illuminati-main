package edu.ucsy.social.service;

import javax.sql.DataSource;

import edu.ucsy.social.data.db.ConnectorFactory;
import edu.ucsy.social.service.impl.UserServiceImpl;

public abstract class ServiceFactory {

	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<T> t, DataSource dataSource) {
		
		if(t.equals(UserService.class)) {
			return (T) new UserServiceImpl(ConnectorFactory.getConnector(dataSource));
		}
		if(t.equals(PostService.class)) {

		}
		
		if(t.equals(CommentService.class)) {
			
		}
		
		if(t.equals(FriendService.class)) {
			
		}

		if(t.equals(CommentService.class)) {
			
		}

		return null;
	}
}
