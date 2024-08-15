package edu.ucsy.social.model.dto.view;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CommentView implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String content;
	private String userName;
	private LocalDateTime updatedAt;

	public CommentView(int id, String content, String userName, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.content = content;
		this.userName = userName;
		this.updatedAt = updatedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
