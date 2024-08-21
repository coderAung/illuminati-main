package edu.ucsy.social.service;

import javax.sql.DataSource;

import edu.ucsy.social.data.annotation.Service;

@Service
public abstract class ServiceFactory {

	public static <T> T getService(Class<T> t, DataSource dataSource) {
		
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
