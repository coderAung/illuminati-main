package edu.ucsy.social.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
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
import edu.ucsy.social.model.dto.view.FriendRequestView;
import edu.ucsy.social.model.entity.FriendRequest;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.service.FriendRequestService;

public class FriendRequestServiceImpl implements FriendRequestService {

	private DatabaseConnector connector;
	
	private Model<FriendRequest> friendRequestModel;
	private Model<User> userModel;
	private Searchable<FriendRequest> friendRequestSearchModel;
	
	public FriendRequestServiceImpl(DatabaseConnector connector) {
		this.connector = connector;

		this.friendRequestModel = ModelFactory.getModel(FriendRequest.class);
		this.userModel = ModelFactory.getModel(User.class);
		
		this.friendRequestSearchModel = ModelFactory.getSearchModel(FriendRequest.class);
	}

	@Override
	public void initConnection(Connection connection) {
		friendRequestModel.setConnection(connection);
		userModel.setConnection(connection);
		friendRequestSearchModel.setConnection(connection);
	}

	@Override
	public void destroyConnection() {
		friendRequestModel.setConnection(null);
		userModel.setConnection(null);
		friendRequestSearchModel.setConnection(null);
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
	public long createFriendRequest(long userId, long requestToUserId) {
		try(var connection = connector.getConnection()) {
			initConnection(connection);
			
			var friendRequest = new FriendRequest(requestToUserId, userId);
			friendRequest = friendRequestModel.save(friendRequest);
			if(null != friendRequest) {
				return friendRequest.id();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return 0;
	}

	@Override
	public boolean deleteFriendRequest(long userId, long requestToUseId) {
		try(var connection = connector.getConnection()) {
			initConnection(connection);
			
			var friendRequest = friendRequestSearchModel.searchOne(
										new Criteria().where("request_to", Type.EQ, requestToUseId)
														.where("request_by", Type.EQ, userId)
									);
			
			if(null != friendRequest) {
				return friendRequestModel.delete(friendRequest.id());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return false;
	}

}