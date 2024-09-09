package edu.ucsy.social.model.dto.view;

import java.io.Serializable;

public class FriendRequestView implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private long friendId;
	private String friendName;
	private String friendProfileImage;

	public FriendRequestView(long id, long friendId, String friendName) {
		this.id = id;
		this.friendName = friendName;
		this.friendId = friendId;
	}

	public FriendRequestView(long id, long friendId) {
		this.id = id;
		this.friendId = friendId;
	}

	public long getFriendId() {
		return friendId;
	}

	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getFriendProfileImage() {
		return friendProfileImage;
	}

	public void setFriendProfileImage(String friendProfileImage) {
		this.friendProfileImage = friendProfileImage;
	}
}
