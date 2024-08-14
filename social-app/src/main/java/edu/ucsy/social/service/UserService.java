package edu.ucsy.social.service;

import edu.ucsy.social.model.dto.view.ProfileDetailView;
import edu.ucsy.social.model.dto.view.ProfileView;

public interface UserService {

	ProfileView getProfileView(int id);

	ProfileDetailView getProfileDetailView(int userid);
}
