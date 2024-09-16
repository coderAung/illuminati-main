package edu.ucsy.social.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToMany;
import edu.ucsy.social.data.OneToOne;
import edu.ucsy.social.data.Searchable;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.data.criteria.Criteria.Type;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.dto.view.PostView;
import edu.ucsy.social.model.dto.view.UserView;
import edu.ucsy.social.model.entity.Comment;
import edu.ucsy.social.model.entity.Friend;
import edu.ucsy.social.model.entity.Post;
import edu.ucsy.social.model.entity.PostImage;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.SavedPost;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.service.SearchingService;
import edu.ucsy.social.utils.DefaultPicture;

public class SearchingServiceImpl implements SearchingService {

	private DatabaseConnector connector;

	private Searchable<Post> postSearchModel;
	private Model<Post> postModel;
	private Searchable<SavedPost> savedPostSearchModel;
	private Model<User> userModel;
	private Searchable<User> userSearchModel;
	private Searchable<Friend> friendSearchModel;

	public SearchingServiceImpl(DatabaseConnector connector) {
		super();
		this.connector = connector;
		this.postSearchModel = ModelFactory.getSearchModel(Post.class);
		this.postModel = ModelFactory.getModel(Post.class);
		this.savedPostSearchModel = ModelFactory.getSearchModel(SavedPost.class);
		this.userModel = ModelFactory.getModel(User.class);
		this.userSearchModel = ModelFactory.getSearchModel(User.class);
		this.friendSearchModel = ModelFactory.getSearchModel(Friend.class);
	}

	@Override
	public void initConnection(Connection connection) {
		postSearchModel.setConnection(connection);
		postModel.setConnection(connection);
		savedPostSearchModel.setConnection(connection);
		userModel.setConnection(connection);
		userSearchModel.setConnection(connection);
		friendSearchModel.setConnection(connection);
	}

	@Override
	public void destroyConnection() {
		postSearchModel.setConnection(null);
		postModel.setConnection(null);
		savedPostSearchModel.setConnection(null);
		userModel.setConnection(null);
		userSearchModel.setConnection(null);
		friendSearchModel.setConnection(null);
	}

	@Override
	public List<PostView> searchPosts(String word, long loginUserId) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);
			var searchCriteria = new Criteria().where("content", Type.LIKE, word.toLowerCase().concat("%"))
					.orderBy("updated_at", Type.DESC);
			var posts = postSearchModel.search(searchCriteria);
			var postViews = new ArrayList<PostView>();
			for (var post : posts) {
				var postView = new PostView(post);

				var postImages = postModel.getRelational(OneToMany.class).getMany(PostImage.class, post.id());

				if (null != postImages) {
					var postImageList = postImages.stream().map(pi -> pi.name()).toList();
					postView.setPostImageList(postImageList);
				}

				var profileImage = userModel.getRelational(OneToOne.class).getOne(ProfileImage.class,
						postView.getUserId());

				if (null != profileImage) {
					postView.setProfileImage(profileImage.name());
				}

				var commentCount = postModel.getRelational(OneToMany.class).countMany(Comment.class, post.id());
				postView.setCommentCount(commentCount);

				// checking if the post is already saved
				var criteria = new Criteria().where("post_id", Type.EQ, post.id()).where("user_id", Type.EQ,
						loginUserId);
				var savedPost = savedPostSearchModel.searchOne(criteria);

				if (null != savedPost) {
					postView.setSaved(true);
				} else {
					postView.setSaved(false);
				}
				postViews.add(postView);
			}
			return postViews;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

	@Override
	public List<UserView> searchUsers(String word, long loginUserId) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);
			var searchCriteria = new Criteria().where("name", Type.LIKE, "%".concat(word.toLowerCase()).concat("%")).where("id", Type.NE, loginUserId);
			var users = userSearchModel.search(searchCriteria);

			if (null != users) {
				var userViews = new ArrayList<UserView>();
				for (var user : users) {
					var uv = new UserView(user);
					var profileImage = userModel.getRelational(OneToOne.class).getOne(ProfileImage.class, user.id());
					if (null != profileImage) {
						uv.setProfileImage(profileImage.name());
					} else {
						uv.setProfileImage(DefaultPicture.defaultProfilePicture);
					}
					// checking is friend
					var isFriend = isFriend(loginUserId, uv.getUserId());
					uv.setFriend(isFriend);

					// calculating mutual friends
					
					userViews.add(uv);
				}

				return userViews;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

	private boolean isFriend(long loginUserId, long otherUserId) {
		var friend = friendSearchModel.searchOne(
				new Criteria().where("user_id", Type.EQ, loginUserId).where("friend_id", Type.EQ, otherUserId));
		if (null != friend) {
			return true;
		}
		return false;
	}

}
