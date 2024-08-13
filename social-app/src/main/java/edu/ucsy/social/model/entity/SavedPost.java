package edu.ucsy.social.model.entity;

public record SavedPost(
		long id, 
		long postId,
		String postContent, 
		long userId) {

	public SavedPost(long postId, String postContent, long userId) {
		this(0, postId, postContent, userId);
	}

	public SavedPost perfectClone(long id) {
		return new SavedPost(id, postId, postContent, userId);
	}
}
