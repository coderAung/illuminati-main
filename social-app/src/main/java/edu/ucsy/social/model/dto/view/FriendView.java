package edu.ucsy.social.model.dto.view;

import java.io.Serializable;

public class FriendView implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
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

	public FriendView(int id, String name, String profileImage) {
		super();
		this.id = id;
		this.name = name;
		this.profileImage = profileImage;
	}

}
