package edu.ucsy.social.authentication;

import java.io.IOException;

import edu.ucsy.social.model.dto.LoginUser;
import edu.ucsy.social.model.entity.User.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/admin/*")
public class AdminAuthenticator extends HttpFilter {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		var session = req.getSession(true);
		if(null == session.getAttribute("loginUser")) {
			res.sendRedirect(getServletContext().getContextPath().concat("/login"));
		} else {
			var loginUser = (LoginUser) session.getAttribute("loginUser");
			if(loginUser.getRole().equals(Role.ADMIN)) {
				chain.doFilter(req, res);
			} else {
				getServletContext().getRequestDispatcher("/view/access-denined.jsp").forward(req, res);
			}
		}
	}
	
}
