package edu.ucsy.social.model.dto;

import java.io.Serializable;

public class OtherUserData implements Serializable {

	private static final long serialVersionUID = 1L;

	private FriendStatus friendStatus;

	public FriendStatus getFriendStatus() {
		return friendStatus;
	}

	public void setFriendStatus(FriendStatus friendStatus) {
		this.friendStatus = friendStatus;
	}

	public enum FriendStatus {
		IS_FRIEND, NEED_TO_CONFIRM, REQUESTED
	}

}
