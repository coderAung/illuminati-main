package edu.ucsy.social.model.dto.view;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class PostView implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String content;
	private LocalDateTime updatedAt;
	private String userName;

	private List<String> postImageList;

	public PostView(int id, String content, LocalDateTime updatedAt, String userName, List<String> postImageList) {
		super();
		this.id = id;
		this.content = content;
		this.updatedAt = updatedAt;
		this.userName = userName;
		this.postImageList = postImageList;
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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getPostImageList() {
		return postImageList;
	}

	public void setPostImageList(List<String> postImageList) {
		this.postImageList = postImageList;
	}

}
