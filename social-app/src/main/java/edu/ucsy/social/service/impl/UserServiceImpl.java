package edu.ucsy.social.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.Countable;
import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToMany;
import edu.ucsy.social.data.OneToOne;
import edu.ucsy.social.data.Searchable;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.data.criteria.Criteria.Type;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.dto.LoginUser;
import edu.ucsy.social.model.dto.form.LoginForm;
import edu.ucsy.social.model.dto.form.ProfileDetailForm;
import edu.ucsy.social.model.dto.form.RegisterForm;
import edu.ucsy.social.model.dto.view.ProfileDetailView;
import edu.ucsy.social.model.dto.view.ProfileView;
import edu.ucsy.social.model.dto.view.UserManagedView;
import edu.ucsy.social.model.entity.CoverImage;
import edu.ucsy.social.model.entity.Post;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.model.entity.User.Status;
import edu.ucsy.social.model.entity.UserDetail;
import edu.ucsy.social.service.UserService;
import edu.ucsy.social.utils.DefaultPicture;

public class UserServiceImpl implements UserService {

	private DatabaseConnector connector;
	private Model<User> userModel;
	private Searchable<User> userSearchModel;
	private Model<UserDetail> userDetailModel;

	public UserServiceImpl(DatabaseConnector connector) {
		this.connector = connector;
		userModel = ModelFactory.getModel(User.class);
		userSearchModel = ModelFactory.getSearchModel(User.class);
		userDetailModel = ModelFactory.getModel(UserDetail.class);
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

	@Override
	public ProfileDetailView getProfileDetailView(long userId) {

		try (var connection = connector.getConnection()) {
			initConnection(connection);
			var user = userModel.findOne(userId);
			if (null == user) {
				return null;
			}
			var profileDetailView = new ProfileDetailView(userId, user.email(), user.name());

			var userDetail = userDetailModel.findOne(userId);
			if (null != userDetail) {
				profileDetailView.setBirthDate(userDetail.birthDate());
				profileDetailView.setAddress(userDetail.address());
				profileDetailView.setBio(userDetail.bio());
				profileDetailView.setGender(userDetail.gender());
				profileDetailView.setRelationship(userDetail.relationship());
				profileDetailView.setOccupation(userDetail.occupation());
				profileDetailView.setPhoneNumber(userDetail.phoneNumber());
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
	public boolean changePassword(String oldPassword, String newPassword, long userId) {

		try (var connection = connector.getConnection()) {
			initConnection(connection);
			var user = userModel.findOne(userId);
			if (null == user) {
				return false;
			}

			if (!user.password().equals(oldPassword)) {
				return false;
			}

			if (user.password().equals(newPassword)) {
				return false;
			}

			user = new User(userId, user.email(), user.name(), newPassword, user.role(), user.status(),
					user.createdAt(), user.updatedAt());
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
		userDetailModel.setConnection(connection);
	}

	@Override
	public void destroyConnection() {
		userModel.setConnection(null);
		userSearchModel.setConnection(null);
		userDetailModel.setConnection(null);
	}

	@Override
	public LoginUser login(LoginForm loginForm) {
		if (null == loginForm.getEmail() || null == loginForm.getPassword()) {
			return null;
		}

		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var criteria = new Criteria().where("email", Type.EQ, loginForm.getEmail()).where("password", Type.EQ,
					loginForm.getPassword());

			var user = userSearchModel.searchOne(criteria);

			if (null == user) {
				return null;
			}

			var profileImage = userModel.getRelational(OneToOne.class).getOne(ProfileImage.class, user.id());
			var coverImage = userModel.getRelational(OneToOne.class).getOne(CoverImage.class, user.id());

			if (null != user) {
				var loginUser = new LoginUser(user);
				if (null != profileImage) {
					loginUser.setProfileImage(profileImage.name());
				}
				if (null != coverImage) {
					loginUser.setCoverImage(coverImage.name());
				}
				return loginUser;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}

		return null;
	}

	@Override
	public boolean createUser(RegisterForm registerForm) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);
			var user = buildUser(registerForm);
			user = userModel.save(user);
			if (null != user) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return false;
	}

	private User buildUser(RegisterForm registerForm) {
		var name = registerForm.getName();
		var email = registerForm.getEmail();
		var password = registerForm.getPassword();

		return new User(email, name, password);
	}

	@Override
	public boolean isEmailExist(String email) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var user = userSearchModel.searchOne(new Criteria().where("email", Type.EQ, email));
			if (null != user) {
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
	public boolean editProfileDetail(ProfileDetailForm profileDetailForm) {
		try (var connection = connector.getConnection()) {

			try {
				initConnection(connection);
				connection.setAutoCommit(false);

				var user = userModel.findOne(profileDetailForm.getUserId());
				if (null != user) {
					if (!user.name().equals(profileDetailForm.getName())) {
						userModel.update(new User(user.id(), user.email(), profileDetailForm.getName(), user.password(),
								user.role(), user.status(), user.createdAt(), user.updatedAt()));
					}

					var userDetail = userDetailModel.findOne(profileDetailForm.getUserId());
					if (null != userDetail) {
						userDetail = getUserDetailFromProfileDetailForm(profileDetailForm);

						userDetail = userDetailModel.update(userDetail);

					} else {
						userDetail = getUserDetailFromProfileDetailForm(profileDetailForm);
						userDetail = userDetailModel.save(userDetail);
					}

					if (null != userDetail) {
						connection.commit();
						return true;
					}
				}
			} catch (Exception e) {
				connection.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return false;
	}

	private UserDetail getUserDetailFromProfileDetailForm(ProfileDetailForm profileDetailForm) {
		return new UserDetail(profileDetailForm.getUserId(), profileDetailForm.getBirthDate(),
				profileDetailForm.getAddress(), profileDetailForm.getBio(), profileDetailForm.getPhoneNumber(),
				profileDetailForm.getGender(), profileDetailForm.getRelationship(), profileDetailForm.getOccupation());
	}

	@Override
	public String getUserName(long userId) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var user = userModel.findOne(userId);
			if (null != user) {
				return user.name();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

	@Override
	public long getTotalUserCount() {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			try {
				var userCounts = Countable.getCountable(userSearchModel).count(null);
				return userCounts;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return 0;
	}

	@Override
	public List<UserManagedView> getUserManagedViews() {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var users = userSearchModel.search(new Criteria().orderBy("created_at", Type.DESC));

			if (null != users && 0 < users.size()) {
				var userManagedViews = new ArrayList<UserManagedView>();

				for (var user : users) {
					var userManagedView = new UserManagedView(user);
					var profileImage = userModel.getRelational(OneToOne.class).getOne(ProfileImage.class, user.id());
					if (null != profileImage) {
						userManagedView.setProfileImage(profileImage.name());
					} else {
						userManagedView.setProfileImage(DefaultPicture.defaultProfilePicture);
					}

					var postCount = userModel.getRelational(OneToMany.class).countMany(Post.class, user.id());
					userManagedView.setPostCount(postCount);

					userManagedViews.add(userManagedView);
				}

				return userManagedViews;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

	@Override
	public long getSuspendedUserCount() {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			try {
				var userCounts = Countable.getCountable(userSearchModel)
						.count(new Criteria().where("status", Type.EQ, Status.SUSPENDED.name()));
				return userCounts;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return 0;
	}

	@Override
	public List<UserManagedView> getUserManagedViews(Status status) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);

			var users = userSearchModel
					.search(new Criteria().where("status", Type.EQ, status.name()).orderBy("created_at", Type.DESC));

			if (null != users && 0 < users.size()) {
				var userManagedViews = new ArrayList<UserManagedView>();

				for (var user : users) {
					var userManagedView = new UserManagedView(user);
					var profileImage = userModel.getRelational(OneToOne.class).getOne(ProfileImage.class, user.id());
					if (null != profileImage) {
						userManagedView.setProfileImage(profileImage.name());
					} else {
						userManagedView.setProfileImage(DefaultPicture.defaultProfilePicture);
					}

					var postCount = userModel.getRelational(OneToMany.class).countMany(Post.class, user.id());
					userManagedView.setPostCount(postCount);

					userManagedViews.add(userManagedView);
				}

				return userManagedViews;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

	@Override
	public boolean suspendUser(int userId) {
		try (var connection = connector.getConnection()) {

			try {
				initConnection(connection);
				connection.setAutoCommit(false);

				var user = userModel.findOne(userId);
				if (null != user && !user.status().equals(Status.SUSPENDED)) {
					user = updateStatus(user, Status.SUSPENDED);
					user = userModel.update(user);

					if (user.status().equals(Status.SUSPENDED)) {
						connection.commit();
						return true;
					}
				}
			} catch (Exception e) {
				connection.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return false;
	}

	private User updateStatus(User user, Status status) {
		return new User(
				user.id(),
				user.email(),
				user.name(),
				user.password(),
				user.role(),
				status,
				user.createdAt(),
				user.updatedAt());
	}

	@Override
	public boolean activateUser(int userId) {
		try (var connection = connector.getConnection()) {

			try {
				initConnection(connection);
				connection.setAutoCommit(false);

				var user = userModel.findOne(userId);
				if (null != user && !user.status().equals(Status.ACTIVE)) {
					user = updateStatus(user, Status.ACTIVE);
					user = userModel.update(user);

					if (user.status().equals(Status.ACTIVE)) {
						connection.commit();
						return true;
					}
				}
			} catch (Exception e) {
				connection.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return false;
	}

	@Override
	public Status checkStatus(long id) {
		try (var connection = connector.getConnection()) {
			initConnection(connection);
			
			var user = userModel.findOne(id);
			if(null != user) {
				return user.status();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroyConnection();
		}
		return null;
	}

}
