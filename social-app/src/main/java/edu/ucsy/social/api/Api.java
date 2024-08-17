package edu.ucsy.social.api;

import edu.ucsy.social.model.dto.LoginUser;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

public abstract class Api extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected LoginUser getLoginUser(HttpServletRequest req) {
		return (LoginUser) req.getSession(true).getAttribute("loginUser");
	}

}
