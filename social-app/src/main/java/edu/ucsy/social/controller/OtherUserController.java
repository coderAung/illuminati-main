package edu.ucsy.social.controller;

import java.io.IOException;

import javax.sql.DataSource;

import edu.ucsy.social.service.OtherUserService;
import edu.ucsy.social.service.PostService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.utils.Limit;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/other/profile"},
		loadOnStartup = 1)
public class OtherUserController extends Controller {

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "social")
	private DataSource dataSource;
	private OtherUserService otherUserService;
	private PostService postService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		otherUserService = ServiceFactory.getService(OtherUserService.class, dataSource);
		postService = ServiceFactory.getService(PostService.class, dataSource);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		
		switch (path) {
		case "/other/profile":
			forwardToOtherProfilePage(req, resp);
			break;
		default:
			break;
		}
	}

	private void forwardToOtherProfilePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get other user id from request parameter
		var otherUserId = Integer.parseInt(req.getParameter("otherUserId"));
		
		// get user's profile view from other user service
		var profileView = otherUserService.getProfileView(otherUserId);
		// set profile view to request scope
		req.setAttribute("profileView", profileView);
		// get 5 friend card view from other user service
		var friendViews = otherUserService.getFriendViews(otherUserId, Limit.FRIEND_CARD);
		// set profile view to request scope
		req.setAttribute("friendViews", friendViews);
		// get 30 post view from other user service
		var postViews = postService.getPostViews(otherUserId, Limit.POST);
		// set profile view to request scope
		req.setAttribute("postViews", postViews);
		// show the view of other user profile
		view(req, resp, "other-profile");
	}
}
