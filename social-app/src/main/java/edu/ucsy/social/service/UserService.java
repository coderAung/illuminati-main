package edu.ucsy.social.service;

import edu.ucsy.social.data.Service;
import edu.ucsy.social.model.dto.LoginUser;
import edu.ucsy.social.model.dto.form.LoginForm;
import edu.ucsy.social.model.dto.form.RegisterForm;
import edu.ucsy.social.model.dto.view.ProfileDetailView;
import edu.ucsy.social.model.dto.view.ProfileView;

public interface UserService extends Service {

	ProfileView getProfileView(long id);

	ProfileDetailView getProfileDetailView(long userId);

	boolean changePassword(String newPassword, long userId);

	LoginUser login(LoginForm loginForm);

	boolean createUser(RegisterForm registerForm);

	boolean isEmailExist(String email);
}
