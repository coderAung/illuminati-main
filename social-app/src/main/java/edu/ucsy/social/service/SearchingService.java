package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.data.Service;
import edu.ucsy.social.model.dto.view.PostView;
import edu.ucsy.social.model.dto.view.UserView;

public interface SearchingService extends Service {

	List<PostView> searchPosts(String word);

	List<UserView> searchUsers(String word);

}
