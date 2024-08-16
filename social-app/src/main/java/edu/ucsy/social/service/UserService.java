package edu.ucsy.social.service;

import edu.ucsy.social.data.annotation.Service;
import edu.ucsy.social.model.dto.view.ProfileDetailView;
import edu.ucsy.social.model.dto.view.ProfileView;

@Service
public interface UserService {

	ProfileView getProfileView(int id);

	ProfileDetailView getProfileDetailView(int userId);

	boolean changePassword(String newPassword, int userId);
}
