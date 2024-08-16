package edu.ucsy.social.model.entity;

import edu.ucsy.social.data.annotation.Entity;

@Entity
public record FriendRequest(
		long id, 
		long requestTo, 
		long requestBy) {

	public FriendRequest(long requestTo, long requestBy) {
		this(0, requestTo, requestBy);
	}
	
	public FriendRequest perfectClone(long id) {
		return new FriendRequest(id, requestTo, requestBy);	
	}
}
