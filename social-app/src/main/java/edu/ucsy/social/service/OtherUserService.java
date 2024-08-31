package edu.ucsy.social.service;

import edu.ucsy.social.data.Service;
import edu.ucsy.social.model.dto.view.FriendView;
import edu.ucsy.social.model.dto.view.ProfileView;

public interface OtherUserService extends Service {

	ProfileView getProfileView(int otherUserId);

	FriendView getFriendViews(int otherUserId, int friendCard);
}
