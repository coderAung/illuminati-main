package edu.ucsy.social.model.dto.form;

import java.io.Serializable;

public class SavePostForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userId;
	private long postId;

	public SavePostForm(long userId, long postId) {
		super();
		this.userId = userId;
		this.postId = postId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}
}
