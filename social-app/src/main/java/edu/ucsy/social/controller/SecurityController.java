package edu.ucsy.social.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = { "/login", "/logout" },
		loadOnStartup = 1)
public class SecurityController extends Controller {

	private static final long serialVersionUID = 1L;

	private static final String LOG_IN = "/login";
	private static final String LOG_OUT = "/logout";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case LOG_IN:
			forwardToLoginPage(req, resp);
			break;
		case LOG_OUT:
			break;
		default:
			break;
		}
	}

	private void forwardToLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		view(req, resp, "login");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get login data from request parameter
		
		// login using user service
		
		// login success -> home page
		// 1. set login user in session
		// 2. redirect to home
		
		// login fail -> redirect to login page with alert message
	}
}
