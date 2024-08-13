package edu.ucsy.social.model.dto.view;

import java.io.Serializable;

public class ProfileView implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String profileImage;

	public ProfileView(String name, String email, String profileImage) {
		super();
		this.name = name;
		this.email = email;
		this.profileImage = profileImage;
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
