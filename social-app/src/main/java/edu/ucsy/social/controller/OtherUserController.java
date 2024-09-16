package edu.ucsy.social.controller;

import java.io.IOException;

import javax.sql.DataSource;

import edu.ucsy.social.model.dto.OtherUserData;
import edu.ucsy.social.service.FriendService;
import edu.ucsy.social.service.ImageService;
import edu.ucsy.social.service.OtherUserService;
import edu.ucsy.social.service.PostService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.service.UserService;
import edu.ucsy.social.utils.DefaultPicture;
import edu.ucsy.social.utils.Limit;
import edu.ucsy.social.utils.StringTool;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/other/profile", "/other/friends", "/other/profile/detail" }, loadOnStartup = 1)
public class OtherUserController extends Controller {

	private static final long serialVersionUID = 1L;

	@Resource(name = "social")
	private DataSource dataSource;
	private OtherUserService otherUserService;
	private FriendService friendService;
	private PostService postService;
	private UserService userService;
	private ImageService imageService;

	@Override
	public void init() throws ServletException {
		super.init();
		otherUserService = ServiceFactory.getService(OtherUserService.class, dataSource);
		friendService = ServiceFactory.getService(FriendService.class, dataSource);
		postService = ServiceFactory.getService(PostService.class, dataSource);
		userService = ServiceFactory.getService(UserService.class, dataSource);
		imageService = ServiceFactory.getService(ImageService.class, dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();

		switch (path) {
		case "/other/profile":
			forwardToOtherProfilePage(req, resp);
			break;
		case "/other/friends":
			forwardToOtherFriendsPage(req, resp);
			break;
		case "/other/profile/detail":
			forwardToOtherDetailPage(req, resp);
			break;
		default:
			break;
		}
	}

	private void forwardToOtherDetailPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!StringTool.isEmpty(req.getParameter("userId"))) {
			var userId = Integer.parseInt(req.getParameter("userId"));
			var profileDetailView = userService.getProfileDetailView(userId);
			
			var profileImage = imageService.getProfileImage(userId);
			var coverImage = imageService.getImageService(userId);
			
			req.setAttribute("profileImage", getImagePath(profileImage, ImageType.PROFILE));
			req.setAttribute("coverImage", getImagePath(coverImage, ImageType.COVER));
			
			req.setAttribute("profileDetailView", profileDetailView);
			view(req, resp, "other-detail");
		}
	}

	private void forwardToOtherFriendsPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!StringTool.isEmpty(req.getParameter("userId"))) {
			var userId = Integer.parseInt(req.getParameter("userId"));
			var friendCount = friendService.getFriendCount(userId);
			req.setAttribute("friendCount", friendCount);

			var friendViews = friendService.getFriendViews(userId, Limit.STARDARD_LIMIT);
			if (null != friendViews) {

				for (var fv : friendViews) {
					if (null == fv.getProfileImage()) {
						fv.setProfileImage(getImagePath(DefaultPicture.defaultProfilePicture, ImageType.PROFILE));
					} else {
						fv.setProfileImage(getImagePath(fv.getProfileImage(), ImageType.PROFILE));
					}
				}

				var userName = userService.getUserName(userId);
				req.setAttribute("userName", userName);
				req.setAttribute("friendViews", friendViews);

				view(req, resp, "other-friends");
			}

		}
	}

	private void forwardToOtherProfilePage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (StringTool.isEmpty(req.getParameter("userId"))) {
			// fail

		} else {

			// get other user id from request parameter
			var otherUserId = Integer.parseInt(req.getParameter("userId"));

			if (otherUserId == getLoginUser(req).getId()) {
				redirect(req, resp, "/profile");
			} else {

				// get user's profile view from other user service
				var profileView = otherUserService.getProfileView(otherUserId);

				if (null == profileView.getProfileImage()) {
					profileView.setProfileImage(getImagePath(DefaultPicture.defaultProfilePicture, ImageType.PROFILE));
				} else {
					profileView.setProfileImage(getImagePath(profileView.getProfileImage(), ImageType.PROFILE));
				}

				if (null == profileView.getCoverImage()) {
					profileView.setCoverImage(getImagePath(DefaultPicture.defaultCoverImage, ImageType.COVER));
				} else {
					profileView.setCoverImage(getImagePath(profileView.getCoverImage(), ImageType.COVER));
				}
				// set profile view to request scope
				req.setAttribute("profileView", profileView);

				var friendViews = friendService.getFriendViews(otherUserId, 5);
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
					var friendCount = friendService.getFriendCount(otherUserId);
					req.setAttribute("friendCount", friendCount);

					// set 5 friend cards to request scope
					req.setAttribute("friendViews", friendViews);

				}

				// get 30 post view from other user service
				var postViews = postService.getPostViews(otherUserId, Limit.POST);

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

					// checking is the post is saved by login user
					var isSaved = postService.isSaved(pv, (int) getLoginUser(req).getId());
					pv.setSaved(isSaved);
				}

				// set profile view to request scope
				req.setAttribute("postViews", postViews);

				// check if the other user is friend or not using friend service
				var loginUserId = getLoginUser(req).getId();
				var friendStatus = friendService.checkFriendStatus(loginUserId, otherUserId);

				// set result to other user data
				var otherUserData = new OtherUserData();
				otherUserData.setFriendStatus(friendStatus);
				// set other user data to request scope
				req.setAttribute("otherUserData", otherUserData);
				// show the view of other user profile
				view(req, resp, "other-profile");
			}
		}
	}
}
