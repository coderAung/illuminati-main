package edu.ucsy.social.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/user/create", "/user/delete"})
public class UserController extends Controller {

	private static final long serialVersionUID = 1L;
	
	private static final String USER_CREATE = "/user/create";
	private static final String USER_DELETE = "/user/delete";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case USER_CREATE:
			forwardToRegisterPage(req, resp);
			break;
		case USER_DELETE:
			
			break;
		default:
			break;
		}
	}

	private void forwardToRegisterPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		view(req, resp, "register");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case USER_CREATE:
			createUser(req, resp);
			break;
		case USER_DELETE:
			deleteUser(req, resp);
			break;
		default:
			break;
		}
	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
		// get user id from login user
		
		// delete all related data of the user by using user service
		
		// redirect to login page
	}

	private void createUser(HttpServletRequest req, HttpServletResponse resp) {
		// get register form data by request parameter
		
		// build register form from the above data
		
		// ask user service to create user
		
		// need to create separate folder for user's
		// profile, cover images
		
		// consider user as logged in
		
		// redirect to home page with alert message SUCCESS
	}
}
