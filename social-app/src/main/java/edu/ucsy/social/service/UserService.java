package edu.ucsy.social.service;

import edu.ucsy.social.data.Service;
import edu.ucsy.social.model.dto.view.ProfileDetailView;
import edu.ucsy.social.model.dto.view.ProfileView;

public interface UserService extends Service {

	ProfileView getProfileView(int id);

	ProfileDetailView getProfileDetailView(int userId);

	boolean changePassword(String newPassword, int userId);
}
