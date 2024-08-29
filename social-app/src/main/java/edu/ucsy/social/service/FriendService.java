package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.data.Service;
import edu.ucsy.social.model.dto.view.FriendView;

public interface FriendService extends Service {

	List<FriendView> getFriendViews(int userid, int limit);

	boolean confirmFriendRequest(int friendRequestId);

	boolean deleteFriend(int userId, int friendId);

}
