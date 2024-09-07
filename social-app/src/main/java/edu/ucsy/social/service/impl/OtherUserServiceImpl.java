package edu.ucsy.social.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToOne;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.dto.view.ProfileView;
import edu.ucsy.social.model.entity.CoverImage;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.service.OtherUserService;

public class OtherUserServiceImpl implements OtherUserService {

	private DatabaseConnector connector;
	
	private Model<User> userModel;

	public OtherUserServiceImpl(DatabaseConnector connector) {
		super();
		this.connector = connector;
		this.userModel = ModelFactory.getModel(User.class);
	}

	@Override
	public void initConnection(Connection connection) {
		userModel.setConnection(connection);
	}

	@Override
	public void destroyConnection() {
		userModel.setConnection(null);
	}

	@Override
	public ProfileView getProfileView(int id) {

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
			var profileView = new ProfileView(user.id(), user.name(), user.email());

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

}
