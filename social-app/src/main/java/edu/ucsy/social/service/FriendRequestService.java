package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.data.Service;
import edu.ucsy.social.model.dto.view.FriendRequestView;

public interface FriendRequestService extends Service {

	List<FriendRequestView> getFriendRequestViews(long userId);

	long createFriendRequest(long userId, long requestToUserId);

	boolean deleteFriendRequest(long userId, long requestTo);

	long getFriendRequestCount(long userId);

}
