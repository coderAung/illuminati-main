package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.model.dto.view.PostView;

public interface PostService {

	List<PostView> getPostViews(int userid, int limit);

}
