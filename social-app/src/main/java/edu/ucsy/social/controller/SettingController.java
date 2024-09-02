package edu.ucsy.social.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/setting", "/setting/change-password", "/setting/changeTheme"},
		loadOnStartup = 1)
public class SettingController extends Controller {

	private static final long serialVersionUID = 1L;
	private static final String SETTING = "/setting";
	private static final String CHANGE_PASSWORD = "/setting/change-password";
	private static final String CHANGE_THEME = "/setting/changeTheme";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    var path = req.getServletPath();
	    switch (path) {
	        case SETTING:
	            forwardToSettingPage(req, resp);
	            break;
	        case CHANGE_PASSWORD:
	            forwardToChangePasswordPage(req, resp);
	            break;
	        case CHANGE_THEME:
	            forwardToChangeThemePage(req, resp);
	            break;
	        default:
	            break;
	    }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String selectedTheme = request.getParameter("theme");
	    request.getSession().setAttribute("theme", selectedTheme);
	    response.sendRedirect(request.getContextPath() + "/setting"); // Redirect to /setting to reapply the theme
	}



	private void forwardToChangePasswordPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		view(req, resp, "change-password");
	}
	

	private void forwardToChangeThemePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		view(req, resp, "Home");
	}
	

	private void forwardToSettingPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		view(req, resp, "setting");
	}
	
	
}
