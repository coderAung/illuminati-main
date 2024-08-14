package edu.ucsy.social.service.impl;

import javax.sql.DataSource;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToOne;
import edu.ucsy.social.data.db.ConnectorFactory;
import edu.ucsy.social.model.dto.view.ProfileDetailView;
import edu.ucsy.social.model.dto.view.ProfileView;
import edu.ucsy.social.model.entity.CoverImage;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.model.entity.UserDetail;
import edu.ucsy.social.service.UserService;

public class UserServiceImpl implements UserService {
	
	private Model<User> userModel;
	
	public UserServiceImpl(DataSource dataSource) {
		this.userModel = ModelFactory.getModel(User.class, ConnectorFactory.getConnector(dataSource));
	}

	@Override
	public ProfileView getProfileView(int id) {
		// get user from database
		var user = userModel.findOne(id);
		
		if(null == user) {
			return null;
		}
		
		// get one profile image related to user from database
		var profileImage = userModel.getRelational(OneToOne.class).getOne(ProfileImage.class, id);
		
		// get one cover image related to user from database
		var coverImage = userModel.getRelational(OneToOne.class).getOne(CoverImage.class, id);
		
		// create profile view
		var profileView = new ProfileView(user.name(), user.email());
		
		if(null != profileImage) {
			profileView.setProfileImage(profileImage.name());
		}
		if(null != coverImage) {
			profileView.setCoverImage(coverImage.name());
		}
		
		return profileView;
	}

	@Override
	public ProfileDetailView getProfileDetailView(int userId) {
		var user = userModel.findOne(userId);
		if(null == user) {
			return null;
		}
		var profileDetailView = new ProfileDetailView(userId, user.email(), user.name());
		
		var userDetail = userModel.getRelational(OneToOne.class).getOne(UserDetail.class, userId);
		if(null != userDetail) {
			profileDetailView.setBirthDate(userDetail.birthDate());
			profileDetailView.setAddress(userDetail.address());
			profileDetailView.setBio(userDetail.bio());
			profileDetailView.setGender(userDetail.gender());
			profileDetailView.setRelationship(userDetail.relationship());
			profileDetailView.setOccupation(userDetail.occupation());
		}
		return profileDetailView;
	}

}
