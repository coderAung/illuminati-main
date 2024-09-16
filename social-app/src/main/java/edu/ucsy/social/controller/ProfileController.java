package edu.ucsy.social.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import javax.sql.DataSource;

import edu.ucsy.social.model.dto.Alert;
import edu.ucsy.social.model.dto.Alert.AlertType;
import edu.ucsy.social.model.dto.form.ProfileDetailForm;
import edu.ucsy.social.model.entity.UserDetail.Gender;
import edu.ucsy.social.model.entity.UserDetail.Occupation;
import edu.ucsy.social.model.entity.UserDetail.Relationship;
import edu.ucsy.social.service.FriendService;
import edu.ucsy.social.service.PostService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.service.UserService;
import edu.ucsy.social.utils.DateTimeUtil;
import edu.ucsy.social.utils.DefaultPicture;
import edu.ucsy.social.utils.Limit;
import edu.ucsy.social.utils.StringTool;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/profile", "/profile/friends", "/profile/detail", "/profile/edit", "/profile/saved",
		"/profile/delete" }, loadOnStartup = 1)
public class ProfileController extends Controller {

	private static final long serialVersionUID = 1L;
	private static final String PROFILE = "/profile";
	private static final String PROFILE_FRIENDS = "/profile/friends";
	private static final String PROFILE_DETAIL = "/profile/detail";
	private static final String PROFILE_EDIT = "/profile/edit";
	private static final String PROFILE_SAVED = "/profile/saved";
	private static final String PROFILE_DELETE = "/profile/delete";

	@Resource(name = "social")
	private DataSource dataSource;
	private UserService userService;
	private PostService postService;
	private FriendService friendService;

	@Override
	public void init() throws ServletException {
		userService = ServiceFactory.getService(UserService.class, dataSource);
		postService = ServiceFactory.getService(PostService.class, dataSource);
		friendService = ServiceFactory.getService(FriendService.class, dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();

		switch (path) {
		case PROFILE:
			forwardToProfilePage(req, resp);
			break;
		case PROFILE_FRIENDS:
			forwardToFriendPage(req, resp);
			break;
		case PROFILE_DETAIL:
			forwardToProfileDetailPage(req, resp);
			break;
		case PROFILE_EDIT:
			forwardToProfileEditPage(req, resp);
			break;
		case PROFILE_SAVED:
			forwardToSavedPostPage(req, resp);
			break;
		case PROFILE_DELETE:
			forwardToDeleteAccountPage(req, resp);
			break;
		default:
			break;
		}

	}

	private void forwardToDeleteAccountPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		view(req, resp, "delete-account");
	}

	private void forwardToSavedPostPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		var loginUser = getLoginUser(req);
		var savedPostViews = postService.getSavedPostViews(loginUser, Limit.STARDARD_LIMIT);

		savedPostViews.forEach(spv -> {
			spv.setDisplayImage(getImagePath(spv.getDisplayImage(), spv.getDisplayImageType()));
		});

		var savedPostCount = postService.countSavedPost(loginUser);

		req.setAttribute("savedPostCount", savedPostCount);

		req.setAttribute("savedPostViews", savedPostViews);
		view(req, resp, "saved-posts");

	}

	private void forwardToProfileEditPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// get user id from loginUser
		var userId = getLoginUser(req).getId();
		// get profile detail view from database
		var profileDetailView = userService.getProfileDetailView(userId);
		// set profile detail view to request scope
		req.setAttribute("profileDetailView", profileDetailView);

		view(req, resp, "profile-edit");
	}

	private void forwardToProfileDetailPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// get user id from loginUser
		var userId = getLoginUser(req).getId();
		// get profile detail view from database
		var profileDetailView = userService.getProfileDetailView(userId);
		// set profile detail view to request scope
		req.setAttribute("profileDetailView", profileDetailView);

		view(req, resp, "profile-detail");
	}

	private void forwardToFriendPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// get id from login user
		var userId = getLoginUser(req).getId();
		// get friend views of login user
		var friendViews = friendService.getFriendViews(userId, Limit.STARDARD_LIMIT);

		for (var fv : friendViews) {
			if (null == fv.getProfileImage()) {
				fv.setProfileImage(getImagePath(DefaultPicture.defaultProfilePicture, ImageType.PROFILE));
			} else {
				fv.setProfileImage(getImagePath(fv.getProfileImage(), ImageType.PROFILE));
			}
		}

		var friendCount = friendService.getFriendCount(userId);

		req.setAttribute("friendCount", friendCount);

		// set friend views request scope
		req.setAttribute("friendViews", friendViews);

		view(req, resp, "friends");
	}

	private void forwardToProfilePage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// get id from login user
		var loginUser = getLoginUser(req);
		var userId = loginUser.getId();

		// get profile card view
		var profileView = userService.getProfileView(userId);

		var profileImage = profileView.getProfileImage();

		if (null == profileImage) {
			var img = getImagePath(DefaultPicture.defaultProfilePicture, ImageType.PROFILE);
			profileView.setProfileImage(img);
		} else {
			var img = getImagePath(profileImage, ImageType.PROFILE);
			profileView.setProfileImage(img);
		}

		var coverImage = profileView.getCoverImage();

		if (null == coverImage) {
			var img = getImagePath(DefaultPicture.defaultCoverImage, ImageType.COVER);
			profileView.setCoverImage(img);
		} else {
			var img = getImagePath(coverImage, ImageType.COVER);
			profileView.setCoverImage(img);
		}
		// set profile card view to request scope
		req.setAttribute("profileView", profileView);

		// get 5 friend views
		var friendViews = friendService.getFriendViews(userId, 5);
		if (null != friendViews && 0 < friendViews.size()) {

			for (var fv : friendViews) {

				if (null == fv.getProfileImage()) {
					var imagePath = getImagePath(DefaultPicture.defaultProfilePicture, ImageType.PROFILE);
					fv.setProfileImage(imagePath);
				} else {
					var imagePath = getImagePath(fv.getProfileImage(), ImageType.PROFILE);
					fv.setProfileImage(imagePath);
				}
			}
			var friendCount = friendService.getFriendCount(userId);
			req.setAttribute("friendCount", friendCount);

			// set 5 friend cards to request scope
			req.setAttribute("friendViews", friendViews);

		}

		// get 30 post views
		var postViews = postService.getPostViews(userId, 30);

		for (var pv : postViews) {
			var postImageList = pv.getPostImageList();
			if (null != postImageList && 0 < postImageList.size()) {
				postImageList = postImageList.stream().map(pi -> getImagePath(pi, ImageType.POST)).toList();
			}
			pv.setPostImageList(postImageList);

			var pi = pv.getProfileImage();
			if (null != pi) {
				pv.setProfileImage(getImagePath(pi, ImageType.PROFILE));
			} else {
				pv.setProfileImage(getImagePath(DefaultPicture.defaultProfilePicture, ImageType.PROFILE));
			}
		}

		// set post views to request scope
		req.setAttribute("postViews", postViews);

		view(req, resp, "profile");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();

		switch (path) {
		case PROFILE_EDIT:
			editProfile(req, resp);
			break;
		case PROFILE_DELETE:
			deleteUser(req, resp);
			break;
		default:
			break;
		}

	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		var loginUser = getLoginUser(req);
		var password = req.getParameter("password");
		if (userService.getPassword(loginUser.getId()).equals(password)) {

			var result = false;

			var postImageList = postService.getPostImageListByUserId(loginUser.getId());

			var coverImageList = userService.getCoverImages(loginUser.getId());

			var profileImageList = userService.getProfileImages(loginUser.getId());

			result = userService.deleteUserAccount(loginUser);
			if (result) {
				var imageFolder = getServletContext().getRealPath("/photo");

				if (null != postImageList) {
//				postImageList = postImageList.stream().map(pi -> getImagePath(pi, ImageType.POST)).toList();
					for (var pi : postImageList) {
						var path = Path.of(imageFolder, DefaultPicture.postFolder, pi);
						Files.deleteIfExists(path);
					}
				}

				if (null != coverImageList) {
//				coverImageList = coverImageList.stream().map(ci -> getImagePath(ci, ImageType.COVER)).toList();
					for (var ci : coverImageList) {
						var path = Path.of(imageFolder, DefaultPicture.coverFolder, ci);
						Files.deleteIfExists(path);
					}
				}

				if (null != profileImageList) {
//				profileImageList = profileImageList.stream().map(pi -> getImagePath(pi, ImageType.PROFILE)).toList();

					for (var pfi : profileImageList) {
						var path = Path.of(imageFolder, DefaultPicture.profileFolder, pfi);
						Files.deleteIfExists(path);
					}
				}
				userService.deleteUser(loginUser.getId());
				redirect(req, resp, "/");
			} else {
				var alert = new Alert("Account Deleting Fails!", AlertType.INFO);
				req.getSession(true).setAttribute("alert", alert);
				redirect(req, resp, "/setting");
			}
		} else {
			var alert = new Alert("Account Deleting Fails!", AlertType.INFO);
			req.getSession(true).setAttribute("alert", alert);
			redirect(req, resp, "/setting");
		}
	}

	private void editProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// get user id from login user
		var loginUser = getLoginUser(req);
		var userId = loginUser.getId();
		// get data from request | data come from front end by edit form
		var name = req.getParameter("name");
		var phoneNumber = req.getParameter("phoneNumber");
		var address = req.getParameter("address");

		var birthDay = req.getParameter("birthDay");
		LocalDate birthDate = null;
		if (!StringTool.isEmpty(birthDay)) {
			birthDate = DateTimeUtil.getBirthDateFromString(birthDay, DateTimeUtil.getDefaultBirthDateFormatter());
		}

		var bio = req.getParameter("bio");
		var gender = req.getParameter("gender");
		var relationship = req.getParameter("relationship");
		var occupation = req.getParameter("occupation");

		// create a edit form dto object
		var profileDetailForm = new ProfileDetailForm(userId);

		profileDetailForm.setName(name);
		profileDetailForm.setPhoneNumber(phoneNumber);
		profileDetailForm.setAddress(address);

		profileDetailForm.setBirthDate(birthDate);
		profileDetailForm.setBio(bio);

		if (!StringTool.DEFAULT_EMPTY_VALUE.equalsIgnoreCase(gender) && null != gender) {
			profileDetailForm.setGender(Gender.valueOf(gender));
		}
		if (!StringTool.DEFAULT_EMPTY_VALUE.equalsIgnoreCase(relationship) && null != relationship) {
			profileDetailForm.setRelationship(Relationship.valueOf(relationship));
		}
		if (!StringTool.DEFAULT_EMPTY_VALUE.equalsIgnoreCase(occupation) && null != occupation) {
			profileDetailForm.setOccupation(Occupation.valueOf(occupation));
		}

		// ask user service to edit using the above form
		var result = userService.editProfileDetail(profileDetailForm);
		// after updating | editing profile go back to profile detail page with alert
		// message
		if (result) {
			redirect(req, resp, "/profile/detail");
		}
	}

}
