package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.data.Service;
import edu.ucsy.social.model.dto.LoginUser;
import edu.ucsy.social.model.dto.form.PostForm;
import edu.ucsy.social.model.dto.form.SavePostForm;
import edu.ucsy.social.model.dto.view.PostDetailView;
import edu.ucsy.social.model.dto.view.PostEditView;
import edu.ucsy.social.model.dto.view.PostView;
import edu.ucsy.social.model.dto.view.SavedPostView;

public interface PostService extends Service {

	List<PostView> getPostViews(long userId, int limit);

	List<PostView> getRandomPostViews(int userId, int limit);

	PostDetailView getPostDetailView(int loginUserId, int postId);

	boolean deletePost(int postId);

	long createPost(PostForm postForm);

	PostView getPostView(int postId);

	PostEditView getPostEditView(int postId);

	boolean editPost(int postId, PostForm postForm);

	boolean saveThePost(SavePostForm savePostForm);

	boolean unsaveThePost(SavePostForm savePostForm);

	boolean isSaved(PostView pv, int loginUserId);

	List<SavedPostView> getSavedPostViews(LoginUser loginUser, int stardardLimit);

	long countSavedPost(LoginUser loginUser);
}
