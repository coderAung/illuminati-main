package edu.ucsy.social.model.dto.view;

import java.io.Serializable;

import edu.ucsy.social.model.dto.OtherUserData;
import edu.ucsy.social.model.entity.User;

public class UserView implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userId;
	private String userName;
	private String profileImage;
	private boolean isFriend;
	private int mutualFriendCount;
	private OtherUserData data;

	public UserView() {
	}

	public OtherUserData getData() {
		return data;
	}

	public void setData(OtherUserData data) {
		this.data = data;
	}

	public UserView(User user) {
		this.userId = user.id();
		this.userName = user.name();
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

	public boolean isFriend() {
		return isFriend;
	}

	public int getMutualFriendCount() {
		return mutualFriendCount;
	}

	public void setMutualFriendCount(int mutualFriendCount) {
		this.mutualFriendCount = mutualFriendCount;
	}

	public void setFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}
	
}
