package edu.ucsy.social.controller;

import java.io.IOException;

import javax.sql.DataSource;

import edu.ucsy.social.service.FriendRequestService;
import edu.ucsy.social.service.ServiceFactory;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/friend-requests"}
		)
public class FriendRequestController extends Controller {

	private static final long serialVersionUID = 1L;
	
	private static final String FRIEND_REQUEST =  "/friend-requests";
	
	@Resource(name = "social")
	private DataSource dataSource;
	private FriendRequestService friendRequestService;
	
	@Override
	public void init() throws ServletException {
		friendRequestService = ServiceFactory.getService(FriendRequestService.class, dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case FRIEND_REQUEST:
			forwardToFriendRequestPage(req, resp);
			break;
		default:
			break;
		}
	}

	private void forwardToFriendRequestPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get user id from login user
		var userId = getLoginUser(req).getId();
		// get friend request views from database using friend request service
		var friendRequestViews = friendRequestService.getFriendRequestViews(userId);
		// set friend request views to request scope
		req.setAttribute("friendRequestViews", friendRequestViews);
		// forward to friend-request page
		view(req, resp, "friend-request");
	}
}
