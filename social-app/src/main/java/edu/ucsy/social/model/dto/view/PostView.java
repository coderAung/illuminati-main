package edu.ucsy.social.model.dto.view;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import edu.ucsy.social.model.entity.Post;

public class PostView implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String content;
	private LocalDateTime updatedAt;
	private long userId;
	private String userName;
	private String profileImage;
	private long commentCount;
	private long shareCount;

	private List<String> postImageList;

	public PostView(long id, String content, LocalDateTime updatedAt, long userId, String userName,
			List<String> postImageList) {
		super();
		this.id = id;
		this.content = content;
		this.updatedAt = updatedAt;
		this.userId = userId;
		this.userName = userName;
		this.postImageList = postImageList;
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

	public PostView(Post post) {
		this.id = post.id();
		this.content = post.content();
		this.updatedAt = post.updatedAt();
		this.userId = post.userId();
		this.userName = post.userName();
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
