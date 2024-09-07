package edu.ucsy.social.model.dto;

import java.io.Serializable;

import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.model.entity.User.Role;

public class LoginUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String email;
	private String name;
	private Role role;
	private Theme theme;

	private String profileImage;
	private String coverImage;

	public LoginUser(long id, String email, String name, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.role = role;
		this.theme = Theme.LIGHT;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public LoginUser(User user) {
		super();
		this.id = user.id();
		this.email = user.email();
		this.name = user.name();
		this.role = user.role();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public enum Theme {
		LIGHT, DARK, BLUE
	}

}
