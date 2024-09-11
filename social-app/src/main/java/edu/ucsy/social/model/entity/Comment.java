package edu.ucsy.social.model.entity;

import java.time.LocalDateTime;

import edu.ucsy.social.data.annotation.Entity;

@Entity
public record Comment(
		long id,
		String content,
		LocalDateTime createdAt,
		LocalDateTime updatedAt,
		long userId,
		String userName,
		long postId) {

	public Comment(String content, long userId, String userName, long postId) {
		this(0, content, null, null, userId, userName, postId);
	}
	
	public Comment(String content, long userId, long postId) {
		this(0, content, null, null, userId, null, postId);
	}

	public Comment perfectClone(long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
		return new Comment(id, content, createdAt, updatedAt, userId, userName, postId);
	}
	
	public Comment updatedClone(LocalDateTime updatedAt) {
		return new Comment(id, content, createdAt, updatedAt, userId, userName, postId);
	}
}
 