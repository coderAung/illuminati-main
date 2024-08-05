package edu.ucsy.social.model.entity;

public record SavedPost(
		long id,
		long postId,
		String postContent,
		long userId) {

}
