package edu.ucsy.social.model.dto.view;

import java.io.Serializable;
import java.time.LocalDateTime;

import edu.ucsy.social.model.entity.Comment;

public class CommentView implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String content;
	private long userId;
	private String userName;
	private String profileImage;
	private LocalDateTime createdAt;

	public CommentView(Comment comment) {
		this.id = comment.id();
		this.content = comment.content();
		this.userId = comment.userId();
		this.userName = comment.userName();
		this.createdAt = comment.updatedAt();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
