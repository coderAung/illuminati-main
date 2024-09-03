package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.data.Service;
import edu.ucsy.social.model.dto.form.PostForm;
import edu.ucsy.social.model.dto.view.PostDetailView;
import edu.ucsy.social.model.dto.view.PostView;

public interface PostService extends Service {

	List<PostView> getPostViews(long userId, int limit);

	List<PostView> getRandomPostViews(int limit);

	PostDetailView getPostDetailView(int postId);

	void deletePost(int postId);

	boolean createPost(PostForm postForm);

}
