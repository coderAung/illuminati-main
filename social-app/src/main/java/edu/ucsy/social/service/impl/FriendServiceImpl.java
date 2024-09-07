package edu.ucsy.social.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToMany;
import edu.ucsy.social.data.OneToOne;
import edu.ucsy.social.data.Searchable;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.data.criteria.Criteria.Type;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.dto.OtherUserData.FriendStatus;
import edu.ucsy.social.model.dto.view.FriendView;
import edu.ucsy.social.model.entity.Friend;
import edu.ucsy.social.model.entity.FriendRequest;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.service.FriendService;

public class FriendServiceImpl implements FriendService {

	private DatabaseConnector connector;

	private Model<Friend> friendModel;
	private Model<FriendRequest> friendRequestModel;
	private Model<User> userModel;
	private Model<ProfileImage> profileImageModel;
	private Searchable<Friend> friendSearchModel;
	private Searchable<FriendRequest> friendRequestSearchModel;

	public FriendServiceImpl(DatabaseConnector connector) {

		this.connector = connector;

		this.friendModel = ModelFactory.getModel(Friend.class);
		this.userModel = ModelFactory.getModel(User.class);
		this.profileImageModel = ModelFactory.getModel(ProfileImage.class);
		this.friendRequestModel = ModelFactory.getModel(FriendRequest.class);

		this.friendSearchModel = ModelFactory.getSearchModel(Friend.class);
		this.friendRequestSearchModel = ModelFactory.getSearchModel(FriendRequest.class);
	}

	@Override
	public void initConnection(Connection connection) {
		friendModel.setConnection(connection);
		friendRequestModel.setConnection(connection);
		userModel.setConnection(connection);
		profileImageModel.setConnection(connection);

		friendSearchModel.setConnection(connection);
		friendRequestSearchModel.setConnection(connection);
	}

	@Override
	public void destroyConnection() {
		friendModel.setConnection(null);
		friendRequestModel.setConnection(null);
		userModel.setConnection(null);
		profileImageModel.setConnection(null);

		friendSearchModel.setConnection(null);
		friendRequestSearchModel.setConnection(null);
	}

	@Override
	public List<FriendView> getFriendViews(long userId, int limit) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var friends = friendSearchModel.search(new Criteria().where("user_id", Type.EQ, userId).limit(limit));

			var friendViews = friends.stream().map(f -> {

				var friendUser = userModel.findOne(f.friendId());

				var friendView = new FriendView(f.id(), friendUser.name());

				var profileImage = userModel.getRelational(OneToOne.class).getOne(ProfileImage.class, friendUser.id());

				if (null != profileImage) {
					friendView.setProfileImage(profileImage.name());
				}

				return friendView;
			}).toList();
			return friendViews;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

	@Override
	public boolean confirmFriendRequest(long userId, long userIdToConfrim) {

		try (var connection = connector.getConnection()) {
			try {

				initConnection(connection);

				connection.setAutoCommit(false);

				var friendRequest = friendRequestSearchModel.searchOne(new Criteria()
						.where("request_to", Type.EQ, userId).where("request_by", Type.EQ, userIdToConfrim));

				if (null == friendRequest) {
					return false;
				}

				var result = friendRequestModel.delete(friendRequest.id());
				if (!result) {
					return false;
				}
				var friend1 = new Friend(userId, userIdToConfrim, null);
				friend1 = friendModel.save(friend1);

				var friend2 = new Friend(userIdToConfrim, userId, null);
				friend2 = friendModel.save(friend2);

				if (null == friend1 && null == friend2) {
					return false;
				}
				connection.commit();
				return true;

			} catch (Exception e) {
				connection.rollback();
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}

		return false;
	}

	@Override
	public boolean deleteFriend(long userId, long friendId) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			try {
				connection.setAutoCommit(false);

				var friend1 = friendSearchModel.searchOne(
						new Criteria().where("user_id", Type.EQ, userId).where("friend_id", Type.EQ, friendId));

				var friend2 = friendSearchModel.searchOne(
						new Criteria().where("user_id", Type.EQ, friendId).where("friend_id", Type.EQ, userId));

				if (null == friend1 && null == friend2) {
					return false;
				}
				
				friendModel.delete(friend1.id());
				friendModel.delete(friend2.id());
				
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
	public boolean isFriendOfLoginUser(long loginUserId, long otherUserId) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);
			var friend = friendSearchModel.searchOne(
					new Criteria().where("user_id", Type.EQ, loginUserId).where("friend_id", Type.EQ, otherUserId));
			if (null != friend) {
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
	public FriendStatus checkFriendStatus(long loginUserId, int otherUserId) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			// checking is friend
			var friend = friendSearchModel.searchOne(
					new Criteria().where("user_id", Type.EQ, loginUserId).where("friend_id", Type.EQ, otherUserId));

			if (null != friend) {
				return FriendStatus.IS_FRIEND;
			}

			// checking if the login user requested to other user
			var loginUserRequestedToOtherUser = friendRequestSearchModel.searchOne(
					new Criteria().where("request_by", Type.EQ, loginUserId).where("request_to", Type.EQ, otherUserId));
			if (null != loginUserRequestedToOtherUser) {
				return FriendStatus.REQUESTED;
			}

			// checking if the other user requested to login user
			var otherUserRequestedToLoginUser = friendRequestSearchModel.searchOne(
					new Criteria().where("request_by", Type.EQ, otherUserId).where("request_to", Type.EQ, loginUserId));
			if (null != otherUserRequestedToLoginUser) {
				return FriendStatus.NEED_TO_CONFIRM;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}

		return null;
	}

	@Override
	public long getFriendCount(long userId) {
		try(var connection = connector.getConnection()) {
			initConnection(connection);
			
			var friendCount = userModel.getRelational(OneToMany.class).countMany(Friend.class, userId);
			return friendCount;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return 0;
	}

}
