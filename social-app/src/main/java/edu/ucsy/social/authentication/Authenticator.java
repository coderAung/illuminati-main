package edu.ucsy.social.authentication;

import java.io.IOException;

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
				}
		)
public class Authenticator extends HttpFilter {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		var loginUser = req.getSession(true).getAttribute("loginUser");
		if(null != loginUser) {
			chain.doFilter(req, resp);
		} else {
			var path = getServletContext().getContextPath().concat("/login");
			resp.sendRedirect(path);
		}
	}
}
