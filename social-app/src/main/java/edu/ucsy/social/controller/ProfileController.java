package edu.ucsy.social.controller;

import java.io.IOException;

import javax.sql.DataSource;

import edu.ucsy.social.service.FriendService;
import edu.ucsy.social.service.PostService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {
				"/profile",
				"/profile/friends",
				"/profile/detail",
				"/profile/edit"},
		loadOnStartup = 1)
public class ProfileController extends Controller {

	private static final long serialVersionUID = 1L;
	private static final String PROFILE = "/profile";
	private static final String PROFILE_FRIENDS = "/profile/friends";
	private static final String PROFILE_DETAIL = "/profile/detail";
	private static final String PROFILE_EDIT = "/profile/edit";
	
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
		default:
			break;
		}
		
	}

	private void forwardToProfileEditPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		view(req, resp, "profile-edit");
	}

	private void forwardToProfileDetailPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		view(req, resp, "profile-detail");
	}

	private void forwardToFriendPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get id from login user
		var userid = getLoginUser(req).getId();
		// get friend views of login user
		var friendViews = friendService.getFriendView(userid, 30);
		// set friend views request scope
		req.setAttribute("friendViews", friendViews);
		
		view(req, resp, "friends");
	}

	private void forwardToProfilePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get id from login user
		var loginUser = getLoginUser(req);
		var userid = loginUser.getId();
		
		// get profile card view
		var profileView = userService.getProfileView(userid);
		
		// set profile card view to request scope
		req.setAttribute("profileView", profileView);
		
		// get 30 post views
		var postViews = postService.getPostViews(userid, 30);
		// set post views to request scope
		req.setAttribute("postViews", postViews);
		
		view(req, resp, "profile");
	}

}
