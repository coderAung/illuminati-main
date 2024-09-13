package edu.ucsy.social.service;

import javax.sql.DataSource;

import edu.ucsy.social.data.Service;
import edu.ucsy.social.data.db.ConnectorFactory;
import edu.ucsy.social.service.impl.CommentServiceImpl;
import edu.ucsy.social.service.impl.FriendRequestServiceImpl;
import edu.ucsy.social.service.impl.FriendServiceImpl;
import edu.ucsy.social.service.impl.ImageServiceImpl;
import edu.ucsy.social.service.impl.OtherUserServiceImpl;
import edu.ucsy.social.service.impl.PostImageServiceImpl;
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
			return (T) new CommentServiceImpl(ConnectorFactory.getConnector(dataSource));
		}
		
		if(t.equals(FriendService.class)) {
			return (T) new FriendServiceImpl(ConnectorFactory.getConnector(dataSource));
		}

		if(t.equals(FriendRequestService.class)) {
			return (T) new FriendRequestServiceImpl(ConnectorFactory.getConnector(dataSource));
		}
		
		if(t.equals(OtherUserService.class)) {
			return (T) new OtherUserServiceImpl(ConnectorFactory.getConnector(dataSource));
		}
		
		if(t.equals(ImageService.class)) {
			return (T) new ImageServiceImpl(ConnectorFactory.getConnector(dataSource));
		}
		
		if(t.equals(PostImageService.class)) {
			return (T) new PostImageServiceImpl(ConnectorFactory.getConnector(dataSource));
		}

		return null;
	}
}
