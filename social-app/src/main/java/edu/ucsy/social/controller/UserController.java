package edu.ucsy.social.controller;

import java.io.IOException;

import javax.sql.DataSource;

import edu.ucsy.social.model.dto.Alert;
import edu.ucsy.social.model.dto.Alert.AlertType;
import edu.ucsy.social.model.dto.form.LoginForm;
import edu.ucsy.social.model.dto.form.RegisterForm;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.service.UserService;
import edu.ucsy.social.utils.StringTool;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/user/create", "/user/delete"})
public class UserController extends Controller {

	private static final long serialVersionUID = 1L;
	
	private static final String USER_CREATE = "/user/create";
	private static final String USER_DELETE = "/user/delete";
	
	@Resource(name = "social")
	private DataSource dataSource;
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		userService = ServiceFactory.getService(UserService.class, dataSource);
	}
	
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

	private void createUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		if(isUserExist(req)) {
			var alert = new Alert("User already exist.", AlertType.INFO);
			req.getSession(true).setAttribute("alert", alert);
			redirect(req, resp, "/register");
		} else {
			// create register form
			var registerForm = getRegisterForm(req);

			// ask user service to create user
			if(null != registerForm) {
				var result = userService.createUser(registerForm);
				// need to create separate folder for user's
				// profile, cover images
				if(result) {
					// consider user as logged in
					
					// redirect to home page with alert message SUCCESS
					var loginUser = userService.login(new LoginForm(registerForm.getEmail(), registerForm.getPassword()));
					if(null != loginUser) {
						req.getSession(true).setAttribute("loginUser", loginUser);
						redirect(req, resp, "/home");
					}
				}
			} else {
				var alert = new Alert("Fail to register", AlertType.DANGER);
				req.getSession(true).setAttribute("alert", alert);
				redirect(req, resp, "/register");
			}
			
		}
	}

	private boolean isUserExist(HttpServletRequest req) {
		var email = req.getParameter("email");
		if(!StringTool.isEmpty(email)) {
			return userService.isEmailExist(email);			
		}
		return false;
	}

	private RegisterForm getRegisterForm(HttpServletRequest req) {
		// get register form data by request parameter
		var name = req.getParameter("name");
		var email = req.getParameter("email");
		var password = req.getParameter("password");
		var confirmPassword = req.getParameter("confirmPassword");
		// build register form from the above data
		if(null != name && null != email &&
				null != password && null != confirmPassword) {
			var registerForm = new RegisterForm(name, email, password, confirmPassword);
			return registerForm;
		}
		
		return null;
	}
}
