package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.data.Service;
import edu.ucsy.social.model.dto.OtherUserData.FriendStatus;
import edu.ucsy.social.model.dto.view.FriendView;

public interface FriendService extends Service {

	List<FriendView> getFriendViews(long userId, int limit);

	boolean confirmFriendRequest(long userId, long userIdToConfrim);

	boolean deleteFriend(long userId, long friendId);

	boolean isFriendOfLoginUser(long loginUserId, long otherUserId);

	FriendStatus checkFriendStatus(long loginUserId, int otherUserId);

}
