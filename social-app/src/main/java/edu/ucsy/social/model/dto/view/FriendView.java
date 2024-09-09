package edu.ucsy.social.model.dto.view;

import java.io.Serializable;

public class FriendView implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private long friendId;
	private String name;
	private String profileImage;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public FriendView(long id, long friendId, String name) {
		super();
		this.id = id;
		this.friendId = friendId;
		this.name = name;
	}

	public long getFriendId() {
		return friendId;
	}

	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}

}
