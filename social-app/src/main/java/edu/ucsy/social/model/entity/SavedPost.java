package edu.ucsy.social.model.entity;

import java.time.LocalDateTime;

public record SavedPost(
		long id, 
		long postId,
		String postContent, 
		long userId,
		LocalDateTime savedAt) {

	public SavedPost(long postId, String postContent, long userId) {
		this(0, postId, postContent, userId, null);
	}

	public SavedPost perfectClone(long id, LocalDateTime savedAt) {
		return new SavedPost(id, postId, postContent, userId, savedAt);
	}
}
