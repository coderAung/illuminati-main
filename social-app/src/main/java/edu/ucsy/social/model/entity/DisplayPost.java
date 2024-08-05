package edu.ucsy.social.model.entity;

public record DisplayPost(
		long id,
		long userId,
		String userName,
		Post post) {

}
