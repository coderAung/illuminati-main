package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.data.annotation.Service;
import edu.ucsy.social.model.dto.view.FriendRequestView;

@Service
public interface FriendRequestService {

	List<FriendRequestView> getFriendRequestViews(int userId);

	boolean createFriendRequest(int userId, int requestToUserId);

	boolean deleteFriendRequest(int friendRequestId);

}
