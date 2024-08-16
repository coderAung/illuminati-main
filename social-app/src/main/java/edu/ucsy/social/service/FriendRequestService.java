package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.model.dto.view.FriendRequestView;

public interface FriendRequestService {

	List<FriendRequestView> getFriendRequestViews(int userId);

}
