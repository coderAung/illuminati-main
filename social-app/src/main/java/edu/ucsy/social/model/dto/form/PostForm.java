package edu.ucsy.social.model.dto.form;

import java.io.Serializable;
import java.util.List;

public class PostForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userId;
	private String userName;
	private String content;

	private List<String> postImages;

	public PostForm(long userId, String userName, String content) {
		this.userId = userId;
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getPostImages() {
		return postImages;
	}

	public void setPostImages(List<String> postImages) {
		this.postImages = postImages;
	}

}
