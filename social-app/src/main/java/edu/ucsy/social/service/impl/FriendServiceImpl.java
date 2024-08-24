package edu.ucsy.social.service.impl;

import java.util.List;

import edu.ucsy.social.model.dto.view.FriendView;
import edu.ucsy.social.service.FriendService;

public class FriendServiceImpl implements FriendService{

	
	
	
	@Override
	public List<FriendView> getFriendViews(int userid, int limit) {
		
		return null;
	}

	@Override
	public boolean confirmFriendRequest(int friendRequestId) {
		return false;
	}

	@Override
	public boolean deleteFriend(int userId, int friendId) {
		return false;
	}

}
