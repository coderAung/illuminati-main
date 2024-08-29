package edu.ucsy.social.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToOne;
import edu.ucsy.social.data.Searchable;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.data.criteria.Criteria.Type;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.dto.LoginUser;
import edu.ucsy.social.model.dto.form.LoginForm;
import edu.ucsy.social.model.dto.view.ProfileDetailView;
import edu.ucsy.social.model.dto.view.ProfileView;
import edu.ucsy.social.model.entity.CoverImage;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.model.entity.UserDetail;
import edu.ucsy.social.service.UserService;

public class UserServiceImpl implements UserService {

	private DatabaseConnector connector;
	private Model<User> userModel;
	private Searchable<User> userSearchModel;
	

	public UserServiceImpl(DatabaseConnector connector) {
		this.connector = connector;
		userModel = ModelFactory.getModel(User.class);
		userSearchModel = ModelFactory.getSearchModel(User.class);
	}

	@Override
	public ProfileView getProfileView(long id) {

		try (var connection = connector.getConnection()) {
			initConnection(connection);

			// get user from database
			var user = userModel.findOne(id);

			if (null == user) {
				return null;
			}

			// get one profile image related to user from database
			var profileImage = userModel.getRelational(OneToOne.class).getOne(ProfileImage.class, id);

			// get one cover image related to user from database
			var coverImage = userModel.getRelational(OneToOne.class).getOne(CoverImage.class, id);

			// create profile view
			var profileView = new ProfileView(user.name(), user.email());

			if (null != profileImage) {
				profileView.setProfileImage(profileImage.name());
			}
			if (null != coverImage) {
				profileView.setCoverImage(coverImage.name());
			}

			return profileView;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}

		return null;
	}

	@Override
	public ProfileDetailView getProfileDetailView(long userId) {

		try (var connection = connector.getConnection()) {
			initConnection(connection);
			var user = userModel.findOne(userId);
			if (null == user) {
				return null;
			}
			var profileDetailView = new ProfileDetailView(userId, user.email(), user.name());

			var userDetail = userModel.getRelational(OneToOne.class).getOne(UserDetail.class, userId);
			if (null != userDetail) {
				profileDetailView.setBirthDate(userDetail.birthDate());
				profileDetailView.setAddress(userDetail.address());
				profileDetailView.setBio(userDetail.bio());
				profileDetailView.setGender(userDetail.gender());
				profileDetailView.setRelationship(userDetail.relationship());
				profileDetailView.setOccupation(userDetail.occupation());
			}
			return profileDetailView;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

	@Override
	public boolean changePassword(String newPassword, long userId) {

		try (var connection = connector.getConnection()) {
			initConnection(connection);
			var user = userModel.findOne(userId);
			if (null == user) {
				return false;
			}
			user = new User(user.email(), user.name(), newPassword);
			var result = userModel.update(user);
			if (null != result) {
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
	public void initConnection(Connection connection) {
		userModel.setConnection(connection);
		userSearchModel.setConnection(connection);
	}

	@Override
	public void destroyConnection() {
		userModel.setConnection(null);
		userSearchModel.setConnection(null);
	}

	@Override
	public LoginUser login(LoginForm loginForm) {
		if(null == loginForm.getEmail() || null == loginForm.getPassword()) {
			return null;
		}
		
		try(var connection = connector.getConnection()) {
			initConnection(connection);
			
			var criteria = new Criteria()
								.where("email", Type.EQ, loginForm.getEmail())
								.where("password", Type.EQ, loginForm.getPassword());
			
			var user = userSearchModel.searchOne(criteria);
			
			if(null != user) {
				var loginUser = new LoginUser(user);
				return loginUser;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		
		return null;
	}

}
