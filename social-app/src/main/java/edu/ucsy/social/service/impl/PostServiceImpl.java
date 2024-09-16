package edu.ucsy.social.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.ucsy.social.controller.Controller.ImageType;
import edu.ucsy.social.data.Countable;
import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToMany;
import edu.ucsy.social.data.OneToOne;
import edu.ucsy.social.data.Searchable;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.data.criteria.Criteria.Type;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.dto.LoginUser;
import edu.ucsy.social.model.dto.form.PostForm;
import edu.ucsy.social.model.dto.form.SavePostForm;
import edu.ucsy.social.model.dto.view.CommentView;
import edu.ucsy.social.model.dto.view.PostDetailView;
import edu.ucsy.social.model.dto.view.PostEditView;
import edu.ucsy.social.model.dto.view.PostView;
import edu.ucsy.social.model.dto.view.SavedPostView;
import edu.ucsy.social.model.entity.Comment;
import edu.ucsy.social.model.entity.Friend;
import edu.ucsy.social.model.entity.Post;
import edu.ucsy.social.model.entity.PostImage;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.SavedPost;
import edu.ucsy.social.model.entity.SharedPost;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.service.PostService;
import edu.ucsy.social.utils.DefaultPicture;

public class PostServiceImpl implements PostService {

	private DatabaseConnector connector;

	private Model<Post> postModel;
	private Model<User> userModel;
	private Model<PostImage> postImageModel;
	private Model<Comment> commentModel;
	private Model<SavedPost> savedPostModel;

	private Searchable<Post> postSearchModel;
	private Searchable<SavedPost> savedPostSearchModel;
	private Searchable<Friend> friendSearchModel;

	public PostServiceImpl(DatabaseConnector connector) {

		this.connector = connector;

		this.postModel = ModelFactory.getModel(Post.class);
		this.userModel = ModelFactory.getModel(User.class);
		this.postImageModel = ModelFactory.getModel(PostImage.class);
		this.commentModel = ModelFactory.getModel(Comment.class);
		this.postSearchModel = ModelFactory.getSearchModel(Post.class);
		this.savedPostModel = ModelFactory.getModel(SavedPost.class);
		this.savedPostSearchModel = ModelFactory.getSearchModel(SavedPost.class);
		this.friendSearchModel = ModelFactory.getSearchModel(Friend.class);
	}

	@Override
	public void initConnection(Connection connection) {
		postModel.setConnection(connection);
		userModel.setConnection(connection);
		postImageModel.setConnection(connection);
		commentModel.setConnection(connection);
		postSearchModel.setConnection(connection);
		savedPostModel.setConnection(connection);
		savedPostSearchModel.setConnection(connection);
		friendSearchModel.setConnection(connection);
	}

	@Override
	public void destroyConnection() {
		postModel.setConnection(null);
		userModel.setConnection(null);
		postImageModel.setConnection(null);
		commentModel.setConnection(null);
		postSearchModel.setConnection(null);
		savedPostModel.setConnection(null);
		savedPostSearchModel.setConnection(null);
		friendSearchModel.setConnection(null);
	}

	@Override
	public List<PostView> getPostViews(long userId, int limit) {

		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var posts = userModel.getRelational(OneToMany.class).getMany(Post.class, userId, limit);
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
				var criteria = new Criteria().where("post_id", Type.EQ, post.id()).where("user_id", Type.EQ, userId);
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
	public List<PostView> getRandomPostViews(int userId, int limit) {

		var requiredLimit = 0;

		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var friends = friendSearchModel.search(new Criteria().where("user_id", Type.EQ, userId));
			if (null == friends || friends.size() == 0) {
				return null;
			}

			Collections.shuffle(friends, new Random());

			var postViewList = new ArrayList<PostView>();
			for (var f : friends) {

				if (requiredLimit == limit) {
					break;
				}

				var criteria = new Criteria().where("user_id", Type.EQ, f.friendId()).limit(3).orderBy("updated_at",
						Type.DESC);
				var posts = postSearchModel.search(criteria);
				if (null != posts) {
					var postViews = posts.stream().map(p -> new PostView(p)).toList();
					for (var pv : postViews) {
						var postImages = postModel.getRelational(OneToMany.class).getMany(PostImage.class, pv.getId());

						if (null != postImages && 0 != postImages.size()) {
							pv.setPostImageList(postImages.stream().map(pi -> pi.name()).toList());
						}

						var profileImage = userModel.getRelational(OneToOne.class).getOne(ProfileImage.class,
								pv.getUserId());

						if (null != profileImage) {
							pv.setProfileImage(profileImage.name());
						}
						var commentCount = postModel.getRelational(OneToMany.class).countMany(Comment.class,
								pv.getId());
						pv.setCommentCount(commentCount);

						// checking if the post is already saved
						var savedPost = savedPostSearchModel.searchOne(
								new Criteria().where("post_id", Type.EQ, pv.getId()).where("user_id", Type.EQ, userId));

						if (null != savedPost) {
							pv.setSaved(true);
						} else {
							pv.setSaved(false);
						}
					}

					requiredLimit = requiredLimit + postViews.size();
					postViewList.addAll(postViews);
				}
			}

			Collections.shuffle(postViewList, new Random());
			return postViewList;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}

		return null;

//		try (var connection = connector.getConnection()) {
//			initConnection(connection);
//
//			List<Post> posts = postModel.getAll();
//			List<Post> shuffledPosts = new ArrayList<>(posts);
//			java.util.Collections.shuffle(shuffledPosts, new Random());
//			List<Post> limitedPosts = shuffledPosts.subList(0, Math.min(limit, shuffledPosts.size()));
//			List<PostView> postViews = new ArrayList<>();
//
//			for (Post post : limitedPosts) {
//				List<PostImage> images = postModel.getRelational(OneToMany.class).getMany(PostImage.class,
//						post.id());
//				List<String> imageNames = new ArrayList<>();
//				if(null != images) {
//					for (PostImage image : images) {
//						imageNames.add(image.name());
//					}
//					
//				}
//				PostView postView = new PostView(post.id(), post.content(), post.updatedAt(), post.userName(),
//						imageNames);
//				postViews.add(postView);
//			}
//			return postViews;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			destroyConnection();
//		}
//		return null;
	}

	@Override
	public PostDetailView getPostDetailView(int loginUserId, int postId) {

		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var post = postModel.findOne(postId);
			if (post == null) {
				return null;
			}

			var postDetailView = new PostDetailView();
			var postView = new PostView(post);

			var postImages = postModel.getRelational(OneToMany.class).getMany(PostImage.class, postId);

			if (null != postImages) {
				postView.setPostImageList(postImages.stream().map(pi -> pi.name()).toList());
				;
			}

			var profileImage = userModel.getRelational(OneToOne.class).getOne(ProfileImage.class, post.userId());
			if (null != profileImage) {
				postView.setProfileImage(profileImage.name());
			}

			// checking if the post is already saved
			var criteria = new Criteria().where("post_id", Type.EQ, postView.getId()).where("user_id", Type.EQ,
					loginUserId);
			var savedPost = savedPostSearchModel.searchOne(criteria);
			if (null != savedPost) {
				postView.setSaved(true);
			} else {
				postView.setSaved(false);
			}

			postDetailView.setPostView(postView);

			var comments = postModel.getRelational(OneToMany.class).getMany(Comment.class, postId, 30);
			List<CommentView> commentViewList = null;
			if (null != comments) {
				commentViewList = comments.stream().map(CommentView::new).toList();
			}

			if (null != commentViewList) {
				commentViewList.forEach(cv -> {
					var userProfileImage = userModel.getRelational(OneToOne.class).getOne(ProfileImage.class,
							cv.getUserId());
					if (null != userProfileImage) {
						cv.setProfileImage(userProfileImage.name());
					}

				});
			}

			postDetailView.setCommentViews(commentViewList);

			return postDetailView;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

	@Override
	public boolean deletePost(int postId) {
		try (var connection = connector.getConnection()) {
			try {
				initConnection(connection);

				connection.setAutoCommit(false);

				// delete post images
				postModel.getRelational(OneToMany.class).deleteMany(PostImage.class, postId);
				// delete comments
				postModel.getRelational(OneToMany.class).deleteMany(Comment.class, postId);
				// delete shares
				postModel.getRelational(OneToMany.class).deleteMany(SharedPost.class, postId);

				// delete saved posts
				postModel.getRelational(OneToMany.class).deleteMany(SavedPost.class, postId);

				// delete the post
				postModel.delete(postId);

				connection.commit();
				return true;
			} catch (Exception e) {
				connection.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return false;

	}

	@Override
	public long createPost(PostForm postForm) {
		try (var connection = connector.getConnection()) {
			try {
				initConnection(connection);

				connection.setAutoCommit(false);

				var post = new Post(postForm.getContent(), postForm.getUserId(), postForm.getUserName());
				post = postModel.save(post);

				if (null != post) {
					if (null != postForm.getPostImages()) {
						for (var postImageName : postForm.getPostImages()) {
							var postImage = new PostImage(postImageName, post.id());
							postImageModel.save(postImage);
						}
					}
				}

				connection.commit();
				return post.id();
			} catch (Exception e) {
				connection.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return 0;
	}

	@Override
	public PostView getPostView(int postId) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var post = postModel.findOne(postId);
			if (null != post) {
				var postView = new PostView(post);
				var postImages = postModel.getRelational(OneToMany.class).getMany(PostImage.class, postId);

				if (null != postImages && postImages.size() > 0) {
					var postImageNames = postImages.stream().map(pi -> pi.name()).toList();
					postView.setPostImageList(postImageNames);
				}

				return postView;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

	@Override
	public PostEditView getPostEditView(int postId) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var post = postModel.findOne(postId);
			if (null != post) {
				var postEditView = new PostEditView(post);
				var postImages = postModel.getRelational(OneToMany.class).getMany(PostImage.class, postId);

				if (null != postImages && postImages.size() > 0) {
					var postImageNames = postImages.stream().map(pi -> {
						var postEditImage = postEditView.new PostEditImage();
						postEditImage.setId(pi.id());
						postEditImage.setImageName(pi.name());
						return postEditImage;
					}).toList();
					postEditView.setPostImageList(postImageNames);
				}

				return postEditView;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

	@Override
	public boolean editPost(int postId, PostForm postForm) {
		try (var connection = connector.getConnection()) {
			try {
				initConnection(connection);

				connection.setAutoCommit(false);
				var post = new Post(postId, postForm.getContent(), postForm.getUserId(), postForm.getUserName());
				post = postModel.update(post);
				if (null != post) {
					if (null != postForm.getPostImages()) {
						for (var postImageName : postForm.getPostImages()) {
							var postImage = new PostImage(postImageName, post.id());
							postImageModel.save(postImage);
						}
					}
				}

				connection.commit();
				return true;
			} catch (Exception e) {
				connection.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return false;
	}

	@Override
	public boolean saveThePost(SavePostForm savePostForm) {

		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var savedPost = new SavedPost(savePostForm.getPostId(), savePostForm.getUserId());
			savedPost = savedPostModel.save(savedPost);
			if (null != savedPost) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}

		return false;
	}

	@Override
	public boolean unsaveThePost(SavePostForm savePostForm) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var savedPost = savedPostSearchModel
					.searchOne(new Criteria().where("post_id", Type.EQ, savePostForm.getPostId()).where("user_id",
							Type.EQ, savePostForm.getUserId()));
			if (null != savedPost) {
				var result = savedPostModel.delete(savedPost.id());
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}

		return false;
	}

	@Override
	public boolean isSaved(PostView pv, int loginUserId) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			// checking if the post is already saved
			var criteria = new Criteria().where("post_id", Type.EQ, pv.getId()).where("user_id", Type.EQ, loginUserId);
			var savedPost = savedPostSearchModel.searchOne(criteria);

			if (null != savedPost) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}

		return false;
	}

	@Override
	public List<SavedPostView> getSavedPostViews(LoginUser loginUser, int stardardLimit) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var savedPosts = userModel.getRelational(OneToMany.class).getMany(SavedPost.class, loginUser.getId(),
					stardardLimit);
			var savedPostViews = new ArrayList<SavedPostView>();

			if (null != savedPosts) {
				for (var savedPost : savedPosts) {
					var savedPostView = new SavedPostView(savedPost);
					// savedPostView.setId(savedPost.id());
					// savedPostView.setPostId(savedPost.postId());
					// savedPostView.setSavedBy(savedPost.userId());
					// savedPostView.setSavedAt(savedPost.savedAt());

					var post = postModel.findOne(savedPost.postId());
					if (null != post) {
						savedPostView.setPostContent(post.content());
						savedPostView.setPostOwnerName(post.userName());
						var postImages = postModel.getRelational(OneToMany.class).getMany(PostImage.class, post.id());
						if (null != postImages && 0 < postImages.size()) {
							savedPostView.setDisplayImage(postImages.get(0).name());
							savedPostView.setDisplayImageType(ImageType.POST);
						} else {
							var profileImage = userModel.getRelational(OneToOne.class).getOne(ProfileImage.class,
									post.userId());
							if (null != profileImage) {
								savedPostView.setDisplayImage(profileImage.name());
								savedPostView.setDisplayImageType(ImageType.PROFILE);
							} else {
								savedPostView.setDisplayImage(DefaultPicture.defaultCoverImage);
								savedPostView.setDisplayImageType(ImageType.COVER);
							}
						}
					}
					savedPostViews.add(savedPostView);
				}

				return savedPostViews;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

	@Override
	public long countSavedPost(LoginUser loginUser) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var savedPostCount = userModel.getRelational(OneToMany.class).countMany(SavedPost.class, loginUser.getId());
			return savedPostCount;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return 0;
	}

	@Override
	public long getTotalPostCount() {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			try {
				var postCount = Countable.getCountable(postSearchModel).count(null);
				return postCount;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return 0;
	}

	@Override
	public List<String> deletePostsByUserId(long id) {
		try (var connection = connector.getConnection()) {
			try {
				initConnection(connection);
				connection.setAutoCommit(false);

			} catch (Exception e) {
				connection.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

	@Override
	public List<String> getPostImageListByUserId(long id) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var posts = postSearchModel.search(new Criteria().where("user_id", Type.EQ, id));
			var postImageList = new ArrayList<PostImage>();
			if (null != posts) {
				for (var post : posts) {
					var postImages = postModel.getRelational(OneToMany.class).getMany(PostImage.class, post.id());
					if (postImages != null && 0 < postImages.size()) {
						postImageList.addAll(postImages);
					}
				}
			}

			return postImageList.stream().map(pi -> pi.name()).toList();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

}