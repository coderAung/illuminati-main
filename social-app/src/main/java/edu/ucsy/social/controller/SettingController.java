package edu.ucsy.social.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/setting", "/setting/change-password"},
		loadOnStartup = 1)
public class SettingController extends Controller {

	private static final long serialVersionUID = 1L;
	private static final String SETTING = "/setting";
	private static final String CHANGE_PASSWORD = "/setting/change-password";

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
		default:
			break;
		}

	}

	private void forwardToChangePasswordPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		view(req, resp, "change-password");
	}
	

	private void forwardToSettingPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		view(req, resp, "setting");
	}
}