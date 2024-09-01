package edu.ucsy.social.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToOne;
import edu.ucsy.social.data.Searchable;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.data.criteria.Criteria.Type;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.dto.view.FriendView;
import edu.ucsy.social.model.entity.Friend;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.service.FriendService;

public class FriendServiceImpl implements FriendService {

	private DatabaseConnector connector;

	private Model<Friend> friendModel;
	private Model<User> userModel;
	private Model<ProfileImage> profileImageModel;
	private Searchable<Friend> friendSearchModel;

	public FriendServiceImpl(DatabaseConnector connector) {

		this.connector = connector;

		this.friendModel = ModelFactory.getModel(Friend.class);
		this.userModel = ModelFactory.getModel(User.class);
		this.profileImageModel = ModelFactory.getModel(ProfileImage.class);

	}

	@Override
	public void initConnection(Connection connection) {
		friendModel.setConnection(connection);
		userModel.setConnection(connection);
		profileImageModel.setConnection(connection);
	}

	@Override
	public void destroyConnection() {
		friendModel.setConnection(null);
		userModel.setConnection(null);
		profileImageModel.setConnection(null);
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
				
								if (null != friendView) {
									friendView.setProfileImage(profileImage.name());
								}

								return friendView;})
					.toList();
			return friendViews;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

	@Override
	public boolean confirmFriendRequest(int friendRequestId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFriend(long userId, long friendId) {
		// TODO Auto-generated method stub
		return false;
	}

}
