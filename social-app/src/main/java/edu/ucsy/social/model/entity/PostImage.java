package edu.ucsy.social.model.entity;

public record PostImage(
		long id,
		String name,
		long postId) {

	public PostImage(String name, long postId) {
		this(0, name, postId);
	}
	
	public PostImage perfectClone(long id) {
		return new PostImage(id, name, postId);
	}
}
