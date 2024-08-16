package edu.ucsy.social.api;

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
		urlPatterns = {
				"/api/friend-request/create",
				"/api/friend-request/delete"
				},
		loadOnStartup = 1
		)
public class FriendRequestApi extends Api {

	private static final long serialVersionUID = 1L;
	
	private static final String FRIEND_REQUEST_CREATE = "/api/friend-request/create";
	private static final String FRIEND_REQUEST_DELETE = "/api/friend-request/delete";
	
	@Resource(name = "social")
	private DataSource dataSource;
	private FriendRequestService friendRequestService;
	
	@Override
	public void init() throws ServletException {
		friendRequestService = ServiceFactory.getService(FriendRequestService.class, dataSource);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case FRIEND_REQUEST_CREATE:
			createFriendRequest(req, resp);
			break;
		case FRIEND_REQUEST_DELETE:
			deleteFriendRequest(req, resp);
			break;
		default:
			break;
		}
	}

	private void createFriendRequest(HttpServletRequest req, HttpServletResponse resp) {
		// get user id from login user
		
		// get request_to for friend request
		
		// save friend request by friendRequestService
		
		// response that the request sent successfully
	}

	private void deleteFriendRequest(HttpServletRequest req, HttpServletResponse resp) {
		// get user id from login user
		
		// get request_to for friend request
		
		// delete | cancel friend request by friendRequestService
		
		// response that the request deleted successfully
	}
}
