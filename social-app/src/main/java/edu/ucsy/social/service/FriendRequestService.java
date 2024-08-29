package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.data.Service;
import edu.ucsy.social.model.dto.view.FriendRequestView;

public interface FriendRequestService extends Service {

	List<FriendRequestView> getFriendRequestViews(int userId);

	boolean createFriendRequest(int userId, int requestToUserId);

	boolean deleteFriendRequest(int friendRequestId);

}
