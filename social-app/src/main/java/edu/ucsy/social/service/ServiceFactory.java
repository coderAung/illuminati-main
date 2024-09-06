package edu.ucsy.social.service;

import javax.sql.DataSource;

import edu.ucsy.social.data.Service;
import edu.ucsy.social.data.db.ConnectorFactory;
import edu.ucsy.social.service.impl.FriendServiceImpl;
import edu.ucsy.social.service.impl.PostServiceImpl;
import edu.ucsy.social.service.impl.UserServiceImpl;

public abstract class ServiceFactory {

	@SuppressWarnings("unchecked")
	public static <T extends Service> T getService(Class<T> t, DataSource dataSource) {
		
		if(t.equals(UserService.class)) {
			return (T) new UserServiceImpl(ConnectorFactory.getConnector(dataSource));
		}
		if(t.equals(PostService.class)) {
			return (T) new PostServiceImpl(ConnectorFactory.getConnector(dataSource));
		}
		
		if(t.equals(CommentService.class)) {
			
		}
		
		if(t.equals(FriendService.class)) {
			return (T) new FriendServiceImpl(ConnectorFactory.getConnector(dataSource));
		}

		if(t.equals(CommentService.class)) {
			
		}

		return null;
	}
}
