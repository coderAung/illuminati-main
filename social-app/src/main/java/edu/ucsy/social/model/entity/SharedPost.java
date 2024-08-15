package edu.ucsy.social.model.entity;

import java.time.LocalDateTime;

import edu.ucsy.social.data.annotation.Entity;

@Entity
public record SharedPost(
		long id, 
		long userId, 
		long postId, 
		String content, 
		LocalDateTime sharedAt) {

	public SharedPost(long userId, long postId, String content) {
		this(0, userId, postId, content, null);
	}
	
	public SharedPost perfectClone(long id, LocalDateTime sharedAt) {
		return new SharedPost(id, userId, postId, content, sharedAt);
	}
	
	public SharedPost updatedClone(LocalDateTime sharedAt) {
		return new SharedPost(id, userId, postId, content, sharedAt);
	}
}
