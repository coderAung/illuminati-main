package edu.ucsy.social.model.dto.view;

import java.io.Serializable;

public class FriendRequestView implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String friendName;
	private String friendProfileImage;

	public FriendRequestView(long id, String friendName) {
		this.id = id;
		this.friendName = friendName;
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
