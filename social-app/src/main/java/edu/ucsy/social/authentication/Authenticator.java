package edu.ucsy.social.authentication;

import java.io.IOException;

import javax.sql.DataSource;

import edu.ucsy.social.model.dto.Alert;
import edu.ucsy.social.model.dto.Alert.AlertType;
import edu.ucsy.social.model.dto.LoginUser;
import edu.ucsy.social.model.entity.User.Status;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(
		urlPatterns = {
				"/home", 
				"/profile/*", 
				"/post/*", 
				"/user/delete",
				"/comment/*",
				"/friend-request",
				"/notifications/*",
				"/setting/*",
				"/other/*",
				"/api/*",
				"/search/*"
				}
		)
public class Authenticator extends HttpFilter {

	private static final long serialVersionUID = 1L;

	@Resource(name = "social")
	private DataSource dataSource;
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = ServiceFactory.getService(UserService.class, dataSource);
	}
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		var loginUser = req.getSession(true).getAttribute("loginUser");
		if(null != loginUser) {
			var user = (LoginUser) loginUser;
			var status = userService.checkStatus(user.getId());
			user.setStatus(status);
			if(user.getStatus().equals(Status.SUSPENDED)) {
				var path = getServletContext().getContextPath().concat("/login");
				var alert = new Alert("Your account is suspended", AlertType.DANGER);
				req.getSession(true).setAttribute("alert", alert);
				resp.sendRedirect(path);
			} else {
				chain.doFilter(req, resp);
			}
		} else {
			var path = getServletContext().getContextPath().concat("/login");
			resp.sendRedirect(path);
		}
	}
}
