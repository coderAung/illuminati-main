package edu.ucsy.social.model.dto.view;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class PostView implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String content;
	private LocalDateTime updatedAt;
	private String userName;
	private long commentCount;
	private long shareCount;

	private List<String> postImageList;

	public PostView(long id, String content, LocalDateTime updatedAt, String userName, List<String> postImageList) {
		super();
		this.id = id;
		this.content = content;
		this.updatedAt = updatedAt;
		this.userName = userName;
		this.postImageList = postImageList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}

	public long getShareCount() {
		return shareCount;
	}

	public void setShareCount(long shareCount) {
		this.shareCount = shareCount;
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
