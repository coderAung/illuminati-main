package edu.ucsy.social.model.entity;

public record Friend(
		long id,
		long userId,
		long friendId,
		String friendName) {

	public Friend(long userId, long friendId) {
		this(0, userId, friendId, null);
	}
}
