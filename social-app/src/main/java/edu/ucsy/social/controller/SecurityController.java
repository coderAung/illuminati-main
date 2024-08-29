package edu.ucsy.social.controller;

import java.io.IOException;

import javax.sql.DataSource;

import edu.ucsy.social.model.dto.Alert;
import edu.ucsy.social.model.dto.Alert.AlertType;
import edu.ucsy.social.model.dto.form.LoginForm;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.service.UserService;
import jakarta.annotation.Resource;
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
		var path = req.getServletPath();
		
		switch (path) {
		case LOG_IN:
			login(req, resp);
			break;

		default:
			break;
		}
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// get login data from request parameter
		var email = req.getParameter("email");
		var password = req.getParameter("password");
		var loginForm = new LoginForm(email, password);
		
		// login using user service
		var loginUser = userService.login(loginForm);
		
		if(null != loginUser) {
			
			// login success -> home page	
			// 1. set login user in session
			// 2. redirect to home
			req.getSession(true).setAttribute("loginUser", loginUser);
			redirect(req, resp, "/home");
		} else {
			// login fail -> redirect to login page with alert message
			var alert = new Alert("Email or Password is incorrect", AlertType.DANGER);
			req.getSession(true).setAttribute("alert", alert);
			redirect(req, resp, "/login");
		}
		
	}

}
