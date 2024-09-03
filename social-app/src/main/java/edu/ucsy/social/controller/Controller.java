package edu.ucsy.social.controller;

import java.io.IOException;

import edu.ucsy.social.model.dto.LoginUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void view(HttpServletRequest req, HttpServletResponse resp, String view)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/view/%s.jsp".formatted(view)).forward(req, resp);
	}

	protected void redirect(HttpServletRequest req, HttpServletResponse resp, String path) throws IOException {
		var url = getServletContext().getContextPath().concat(path);
		resp.sendRedirect(url);
	}

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

	public enum ImageType {
		PROFILE, COVER, POST
	}

}