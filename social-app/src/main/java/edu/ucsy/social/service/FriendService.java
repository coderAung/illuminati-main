package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.model.dto.view.FriendView;

public interface FriendService {

	List<FriendView> getFriendView(int userid, int limit);

}
