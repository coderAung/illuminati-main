package edu.ucsy.social.model.dto.form;

import java.io.Serializable;

public class CommentForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private long postId;
	private long userId;
	private String userName;
	private String commentContent;

	public CommentForm(long postId, long userId, String userName, String commentContent) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.userName = userName;
		this.commentContent = commentContent;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
