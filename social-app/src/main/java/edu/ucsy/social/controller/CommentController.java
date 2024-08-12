package edu.ucsy.social.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/post/comment"},
		loadOnStartup = 1)
public class CommentController extends Controller {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case "/post/comment":
			forwardToPostDetailPage(req, resp);
			break;

		default:
			break;
		}
	}

	private void forwardToPostDetailPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		view(req, resp, "post-detail");
	}
}
