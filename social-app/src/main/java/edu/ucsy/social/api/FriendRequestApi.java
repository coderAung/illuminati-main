package edu.ucsy.social.api;

import java.io.IOException;
import java.util.Map;

import javax.sql.DataSource;

import edu.ucsy.social.service.FriendRequestService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.utils.JsonTool;
import edu.ucsy.social.utils.StringTool;
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
			cancelFriendRequest(req, resp);
			break;
		default:
			break;
		}
	}

	private void createFriendRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// get user id from login user
		var userId = getLoginUser(req).getId();
		// get request_to for friend request
		if(StringTool.isEmpty(req.getParameter("requestTo"))) {
			// fail
		}
		var requestToUserId = Integer.parseInt(req.getParameter("requestTo"));
		
		// save friend request by friendRequestService
		var result = friendRequestService.createFriendRequest(userId, requestToUserId);
		
		// response that the request sent successfully
		if(result) {
			resp.setContentType("application/json");
			var writer = resp.getWriter();
			writer.append(
					JsonTool.jsonFromMap(
							Map.of(
								"status", "success",
								"requestTo", requestToUserId
							)));
			writer.flush();
		}
	}

	private void cancelFriendRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// get friend request id from request attribute
		if(StringTool.isEmpty(req.getParameter("friendRequestId"))) {
			// fail
		}
		var friendRequestId = Integer.parseInt(req.getParameter("friendRequestId"));
		
		// delete | cancel friend request by friendRequestService
		var result = friendRequestService.deleteFriendRequest(friendRequestId);

		// response that the request deleted successfully
		if(result) {
			resp.setContentType("application/json");
			var writer = resp.getWriter();
			writer.append(
					JsonTool.jsonFromMap(
							Map.of(
								"status", "success",
								"deletedFriendRequestId", friendRequestId
							)));
			writer.flush();
		}
	}
}