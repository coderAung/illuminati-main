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

	public Comment(String content, long userId, long postId) {
		this(0, content, null, null, userId, null, postId);
	}
	
	public Comment perfectClone(long id, LocalDateTime createdAt, LocalDateTime updatedAt, String userName) {
		return new Comment(id, content, createdAt, updatedAt, userId, userName, postId);
	}
}
