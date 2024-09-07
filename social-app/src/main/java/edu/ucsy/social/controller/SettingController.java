package edu.ucsy.social.controller;

import java.io.IOException;

import javax.sql.DataSource;

import edu.ucsy.social.model.dto.Alert;
import edu.ucsy.social.model.dto.Alert.AlertType;
import edu.ucsy.social.model.dto.LoginUser.Theme;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/setting", "/setting/change-password", "/setting/change-theme"},
		loadOnStartup = 1)
public class SettingController extends Controller {

	private static final long serialVersionUID = 1L;
	private static final String SETTING = "/setting";
	private static final String CHANGE_PASSWORD = "/setting/change-password";
	private static final String CHANGE_THEME = "/setting/change-theme";

	@Resource(name = "social")
	private DataSource dataSource;
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = ServiceFactory.getService(UserService.class, dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case SETTING:
			forwardToSettingPage(req, resp);
			break;
		case CHANGE_PASSWORD:
			break;
		default:
			break;
		}
	}

	private void forwardToSettingPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		view(req, resp, "setting");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case SETTING:

			break;
		case CHANGE_PASSWORD:
			changePassword(req, resp);
			break;
		case CHANGE_THEME:
			changeTheme(req, resp);
		default:
			break;
		}		
	}

	private void changeTheme(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// get theme from request parameter
		var theme = req.getParameter("theme");
		// set the theme to login user theme
		getLoginUser(req).setTheme(Theme.valueOf(theme.toUpperCase()));
		// redirect to setting page
		redirect(req, resp, "/setting");
	}

	private void changePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//get user id from login user
		var userId = getLoginUser(req).getId();
		
		// get new password from request parameter
		var newPassword = req.getParameter("newPassword");
		
		// change password using user service
		var result = userService.changePassword(newPassword, userId);
		
		// redirect to profile page with alert message for changing password
		if(result) {
			var alert = new Alert("Password is successfully changed!", AlertType.INFO);
			req.getSession(true).setAttribute("alert", alert);
		} else {
			var alert = new Alert("Password changing is failed!", AlertType.DANGER);
			req.getSession(true).setAttribute("alert", alert);
		}
		redirect(req, resp, "/profile");
	}
}
