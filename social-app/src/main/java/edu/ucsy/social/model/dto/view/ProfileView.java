package edu.ucsy.social.model.dto.view;

import java.io.Serializable;

public class ProfileView implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userId;
	private String name;
	private String email;
	private String profileImage;
	private String coverImage;

	public ProfileView(long userId, String name, String email) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
	}

	public ProfileView(long userId, String name, String email, String profileImage, String coverImage) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.profileImage = profileImage;
		this.coverImage = coverImage;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

}
