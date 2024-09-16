package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.data.Service;
import edu.ucsy.social.model.dto.LoginUser;
import edu.ucsy.social.model.dto.form.LoginForm;
import edu.ucsy.social.model.dto.form.ProfileDetailForm;
import edu.ucsy.social.model.dto.form.RegisterForm;
import edu.ucsy.social.model.dto.view.ProfileDetailView;
import edu.ucsy.social.model.dto.view.ProfileView;
import edu.ucsy.social.model.dto.view.UserManagedView;
import edu.ucsy.social.model.entity.User.Status;

public interface UserService extends Service {

	ProfileView getProfileView(long id);

	ProfileDetailView getProfileDetailView(long userId);

	boolean changePassword(String oldPassword, String newPassword, long userId);

	LoginUser login(LoginForm loginForm);

	boolean createUser(RegisterForm registerForm);

	boolean isEmailExist(String email);

	boolean editProfileDetail(ProfileDetailForm profileDetailForm);

	String getUserName(long userId);

	long getTotalUserCount();

	List<UserManagedView> getUserManagedViews();

	long getSuspendedUserCount();

	List<UserManagedView> getUserManagedViews(Status status);

	boolean suspendUser(int userId);

	boolean activateUser(int userId);
}
