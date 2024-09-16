package edu.ucsy.social.model.dto.view;

import java.io.Serializable;
import java.time.LocalDateTime;

import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.model.entity.User.Role;
import edu.ucsy.social.model.entity.User.Status;

public class UserManagedView implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userId;
	private String userName;
	private String profileImage;
	private Status status;
	private Role role;
	private LocalDateTime createdAt;

	private long postCount;
	
	public UserManagedView(User user) {
		this.userId = user.id();
		this.userName = user.name();
		this.status = user.status();
		this.role = user.role();
		this.createdAt = user.createdAt();
	}

	public LocalDateTime getCreatedAtt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public long getPostCount() {
		return postCount;
	}

	public void setPostCount(long postCount) {
		this.postCount = postCount;
	}

}
