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

	public Post(String content, List<PostImage> postImages, long userId) {
		this(0, content, postImages, null, null, userId, null);
	}
	
	public Post perfectClone(long id, List<PostImage> postImages, LocalDateTime createdAt, LocalDateTime updatedAt) {
		return new Post(id, content, postImages, createdAt, updatedAt, userId, userName);
	}
	
	public Post updatedClone(LocalDateTime updatedAt) {
		return new Post(id, content, postImages, createdAt, updatedAt, userId, userName);
	}
	
	public Post updatedClone(LocalDateTime updatedAt) {
		return new Post(id, content, postImages, createdAt, updatedAt, userId, userName);
	}
}
