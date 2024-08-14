package edu.ucsy.social.model.entity;

import edu.ucsy.social.data.annotation.Entity;

@Entity
public record Friend(
		long id,
		long userId,
		long friendId,
		String friendName) {

	public Friend(long userId, long friendId, String friendName) {
		this(0, userId, friendId, friendName);
	}
	
	public Friend perfectClone(long id) {
		return new Friend(id, userId, friendId, friendName);
	}
}
