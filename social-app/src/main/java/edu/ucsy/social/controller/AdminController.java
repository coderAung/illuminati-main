
package edu.ucsy.social.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import edu.ucsy.social.model.dto.view.UserManagedView;
import edu.ucsy.social.model.entity.User.Status;
import edu.ucsy.social.service.PostService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
		"/admin", 
		"/admin/users/*", 
		"/admin/dashboard",
		}, loadOnStartup = 1)
public class AdminController extends Controller {

	private static final long serialVersionUID = 1L;
	
	private static final String ADMIN = "/admin";
	private static final String ADMIN_DASHBOARD = "/admin/dashboard";
	private static final String ADMIN_USERS = "/admin/users";
	
	@Resource(name = "social")
	private DataSource dataSource;
	private UserService userService;
	private PostService postService;
	
	@Override
	public void init() throws ServletException {
		userService = ServiceFactory.getService(UserService.class, dataSource);
		postService = ServiceFactory.getService(PostService.class, dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case ADMIN:
		case ADMIN_DASHBOARD:
			goToAdminDashboard(req, resp);
			break;
		case ADMIN_USERS:
			showUsersInDashBoard(req, resp);
			break;
		default:
			break;
		}
	}

	private void showUsersInDashBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var userCount = userService.getTotalUserCount();
		req.setAttribute("userCount", userCount);
		
		var suspendedUserCount = userService.getSuspendedUserCount();
		req.setAttribute("suspendedUserCount", suspendedUserCount);
		
		var postCount = postService.getTotalPostCount();
		req.setAttribute("postCount", postCount);
		
		var info = req.getPathInfo();
		List<UserManagedView> userManagedViews = null;
		if("/active".equals(info)) {
			req.setAttribute("active", "activeUsers");
			userManagedViews = userService.getUserManagedViews(Status.ACTIVE);
		} else if("/suspended".equals(info)) {
			req.setAttribute("active", "suspendedUsers");
			userManagedViews = userService.getUserManagedViews(Status.SUSPENDED);
		} else if("/".equals(info)) {
			req.setAttribute("active", "dashboard");
			userManagedViews = userService.getUserManagedViews();
		}

		if(null != userManagedViews) {
			userManagedViews.forEach(view -> view.setProfileImage(getImagePath(view.getProfileImage(), ImageType.PROFILE)));
		}
		req.setAttribute("userManagedViews", userManagedViews);
		view(req, resp, "admin/dashboard");
	}

	private void goToAdminDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var userCount = userService.getTotalUserCount();
		req.setAttribute("userCount", userCount);
		
		var suspendedUserCount = userService.getSuspendedUserCount();
		req.setAttribute("suspendedUserCount", suspendedUserCount);
		
		var postCount = postService.getTotalPostCount();
		req.setAttribute("postCount", postCount);
		
		var userManagedViews = userService.getUserManagedViews();
		if(null != userManagedViews) {
			userManagedViews.forEach(view -> view.setProfileImage(getImagePath(view.getProfileImage(), ImageType.PROFILE)));
		}
		req.setAttribute("active", "dashboard");
		req.setAttribute("userManagedViews", userManagedViews);
		view(req, resp, "admin/dashboard");
	}
	
}
