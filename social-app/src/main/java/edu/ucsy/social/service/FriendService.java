package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.model.dto.view.FriendView;

public interface FriendService {

	List<FriendView> getFriendViews(int userid, int limit);

}
