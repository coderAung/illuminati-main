package edu.ucsy.social.model.entity;

import java.time.LocalDateTime;

import edu.ucsy.social.data.annotation.Entity;

@Entity
public record SavedPost(
		long id, 
		long postId,
		long userId,
		LocalDateTime savedAt) {

	public SavedPost(long postId, long userId) {
		this(0, postId, userId, null);
	}
	
	public SavedPost perfectClone(long id, LocalDateTime savedAt) {
		return new SavedPost(id, postId, userId, savedAt);
	}
}
