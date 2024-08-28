package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.data.annotation.Service;
import edu.ucsy.social.model.dto.view.FriendView;

@Service
public interface FriendService {

	List<FriendView> getFriendViews(int userid, int limit);

	boolean confirmFriendRequest(int friendRequestId);

	boolean deleteFriend(int userId, int friendId);

}
