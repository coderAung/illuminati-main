package edu.ucsy.social.api;

import java.io.IOException;
import java.util.Map;

import javax.sql.DataSource;

import edu.ucsy.social.service.FriendService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.utils.JsonTool;
import edu.ucsy.social.utils.StringTool;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/api/friend/create", "/api/friend/delete" }, loadOnStartup = 1)
public class FriendApi extends Api {

	private static final long serialVersionUID = 1L;

	private static final String FRIEND_CREATE = "/api/friend/create";
	private static final String FRIEND_DELETE = "/api/friend/delete";

	@Resource(name = "social")
	private DataSource dataSource;
	private FriendService friendService;

	@Override
	public void init() throws ServletException {
		friendService = ServiceFactory.getService(FriendService.class, dataSource);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case FRIEND_CREATE:
			createFriend(req, resp);
			break;
		case FRIEND_DELETE:
			deleteFriend(req, resp);
			break;
		default:
			break;
		}
	}

	private void deleteFriend(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// get user id from login user
		var userId = getLoginUser(req).getId();
		// get friend id from request parameter
		if(StringTool.isEmpty(req.getParameter("friendId"))) {
			// fail
		}
		var friendId = Integer.parseInt(req.getParameter("friendId"));
		// delete them being friend by friendService
		var result = friendService.deleteFriend(userId, friendId);
		// respond an alert message for removing friend
		if (result) {
			resp.setContentType("application/json");
			var writer = resp.getWriter();
			writer.append(JsonTool.jsonFromMap(Map.of("result", "success")));
			writer.flush();
		}
	}

	private void createFriend(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// get friend request id from request parameter
		if (StringTool.isEmpty(req.getParameter("userIdToConfrim"))) {
			// fail
		}
		var userIdToConfrim = Integer.parseInt(req.getParameter("userIdToConfrim"));

		// confirm | create friends by friend service
		var result = friendService.confirmFriendRequest(getLoginUser(req).getId(), userIdToConfrim);

		// respond an alert message for being friend
		if (result) {
			resp.setContentType("application/json");
			var writer = resp.getWriter();
			writer.append(JsonTool.jsonFromMap(Map.of("result", "success")));
			writer.flush();
		}
	}
}
