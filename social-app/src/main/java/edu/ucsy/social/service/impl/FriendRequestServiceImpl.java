package edu.ucsy.social.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToMany;
import edu.ucsy.social.data.OneToOne;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.dto.view.FriendRequestView;
import edu.ucsy.social.model.entity.FriendRequest;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.service.FriendRequestService;

public class FriendRequestServiceImpl implements FriendRequestService {

	private DatabaseConnector connector;
	
	private Model<FriendRequest> friendRequestModel;
	private Model<User> userModel;
	
	public FriendRequestServiceImpl(DatabaseConnector connector) {
		this.friendRequestModel = ModelFactory.getModel(FriendRequest.class);
		this.userModel = ModelFactory.getModel(User.class);
		this.connector = connector;
	}

	@Override
	public void initConnection(Connection connection) {
		friendRequestModel.setConnection(connection);
		userModel.setConnection(connection);
	}

	@Override
	public void destroyConnection() {
		friendRequestModel.setConnection(null);
		userModel.setConnection(null);
	}

	@Override
	public List<FriendRequestView> getFriendRequestViews(long userId) {
		
		try(var connection = connector.getConnection()) {
			initConnection(connection);
			var friendRequests = userModel.getRelational(OneToMany.class).getMany(FriendRequest.class, userId);
			var friendRequestViews = friendRequests.stream()
													.map(fr -> {
														var rs = userModel.findOne(fr.requestBy(), "name");
														
														return getFriendRequestView(fr, rs);
													}).toList();
			friendRequestViews.forEach(frv -> {
				var profileImage = userModel.getRelational(OneToOne.class).getOne(ProfileImage.class, frv.getFriendId());
				if(null != profileImage) {
					frv.setFriendProfileImage(profileImage.name());
				}
			});
			
			return friendRequestViews;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		
		return null;
	}
	
	private FriendRequestView getFriendRequestView(FriendRequest fr, ResultSet rs) {
		try {
			if(rs.next()) {
				var friendRequestView = new FriendRequestView(fr.id(), fr.requestBy(), rs.getString("name"));
				return friendRequestView;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean createFriendRequest(long userId, long requestToUserId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFriendRequest(int friendRequestId) {
		// TODO Auto-generated method stub
		return false;
	}

}