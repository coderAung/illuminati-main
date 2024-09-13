package edu.ucsy.social.model.entity;

import java.time.LocalDateTime;

import edu.ucsy.social.data.annotation.Entity;

@Entity
public record Post(
		long id,
		String content,
		LocalDateTime createdAt,
		LocalDateTime updatedAt,
		long userId,
		String userName) {

	public Post(String content, long userId, String userName) {
		this(0, content, null, null, userId, userName);
	}

	
	public Post(int postId, String content, long userId, String userName) {
		this(postId, content, null, null, userId, userName);
	}

	public Post perfectClone(long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
		return new Post(id, content, createdAt, updatedAt, userId, userName);
	}
	
	public Post updatedClone(LocalDateTime updatedAt) {
		return new Post(id, content, createdAt, updatedAt, userId, userName);
	}
}

