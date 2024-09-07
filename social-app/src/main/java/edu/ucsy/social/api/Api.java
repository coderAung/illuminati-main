package edu.ucsy.social.api;

import edu.ucsy.social.controller.Controller.ImageType;
import edu.ucsy.social.model.dto.LoginUser;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

public abstract class Api extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected LoginUser getLoginUser(HttpServletRequest req) {
		return (LoginUser) req.getSession(true).getAttribute("loginUser");
	}
	
	protected String getImagePath(String image, ImageType type) {

		return switch (type) {
		case PROFILE -> getServletContext().getContextPath().concat("/photo/profile/%s".formatted(image));
		case COVER -> getServletContext().getContextPath().concat("/photo/cover/%s".formatted(image));
		case POST -> getServletContext().getContextPath().concat("/photo/post/%s".formatted(image));
		};
	}

}
