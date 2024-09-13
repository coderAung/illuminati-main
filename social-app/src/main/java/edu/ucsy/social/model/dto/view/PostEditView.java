package edu.ucsy.social.model.dto.view;

import java.io.Serializable;
import java.util.List;

import edu.ucsy.social.model.entity.Post;

public class PostEditView implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String content;
	private long userId;
	private String userName;
	private String profileImage;

	private List<PostEditImage> postImageList;
	
	public PostEditView(Post post) {
		this.id = post.id();
		this.content = post.content();
		this.userId = post.userId();
		this.userName = post.userName();
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

	public List<PostEditImage> getPostImageList() {
		return postImageList;
	}

	public void setPostImageList(List<PostEditImage> postImageList) {
		this.postImageList = postImageList;
	}

	public class PostEditImage {
		private long id;
		private String imageName;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getImageName() {
			return imageName;
		}

		public void setImageName(String imageName) {
			this.imageName = imageName;
		}

	}

}
