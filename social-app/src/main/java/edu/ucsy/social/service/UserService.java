package edu.ucsy.social.service;

import edu.ucsy.social.model.dto.view.ProfileView;

public interface UserService {

	ProfileView getProfileView(int id);
}
