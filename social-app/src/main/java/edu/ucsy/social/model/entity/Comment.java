package edu.ucsy.social.model.entity;

import java.time.LocalDateTime;

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
	
	public Comment perfectClone(long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
		return new Comment(id, content, createdAt, updatedAt, userId, userName, postId);
	}
}
