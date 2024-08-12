package edu.ucsy.social.model.entity;

import java.time.LocalDateTime;
import java.util.List;

public record Post(
		long id,
		String content,
		List<PostImage> postImages,
		LocalDateTime createdAt,
		LocalDateTime updatedAt,
		long userId,
		String userName) {

	public Post(String content, List<PostImage> postImages, long userId, String userName) {
		this(0, content, postImages, null, null, userId, userName);
	}
	
	public Post perfectClone(long id, LocalDateTime createdAt, LocalDateTime updatedAt, String string) {
		return new Post(id, content, null, createdAt, updatedAt, userId, userName);
	}
	
	public Post updatedClone(LocalDateTime updatedAt) {
		return new Post(id, content, postImages, createdAt, updatedAt, userId, userName);
	}
}
