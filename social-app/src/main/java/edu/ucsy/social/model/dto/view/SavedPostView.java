package edu.ucsy.social.model.dto.view;

import java.io.Serializable;
import java.time.LocalDateTime;

import edu.ucsy.social.controller.Controller.ImageType;
import edu.ucsy.social.model.entity.SavedPost;

public class SavedPostView implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private long postId;
	private long savedBy;

	private String postOwnerName;
	private LocalDateTime savedAt;
	private String postContent;
	private String displayImage;
	private ImageType displayImageType;
	
	public SavedPostView() {}

	public SavedPostView(SavedPost savedPost) {
		this.id = savedPost.id();
		this.postId = savedPost.postId();
		this.savedBy = savedPost.userId();
		this.savedAt = savedPost.savedAt();
	}
	
	public long getId() {
		return id;
	}

	public ImageType getDisplayImageType() {
		return displayImageType;
	}

	public void setDisplayImageType(ImageType displayImageType) {
		this.displayImageType = displayImageType;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getSavedBy() {
		return savedBy;
	}

	public void setSavedBy(long savedBy) {
		this.savedBy = savedBy;
	}

	public String getPostOwnerName() {
		return postOwnerName;
	}

	public void setPostOwnerName(String postOwnerName) {
		this.postOwnerName = postOwnerName;
	}

	public LocalDateTime getSavedAt() {
		return savedAt;
	}

	public void setSavedAt(LocalDateTime savedAt) {
		this.savedAt = savedAt;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getDisplayImage() {
		return displayImage;
	}

	public void setDisplayImage(String displayImage) {
		this.displayImage = displayImage;
	}

}
