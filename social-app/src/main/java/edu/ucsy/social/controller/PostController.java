package edu.ucsy.social.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {
				"/post", 
				"/post/create",
				"/post/edit",
				"/post/delete"},
		loadOnStartup = 1)
public class PostController extends Controller {

	private static final long serialVersionUID = 1L;

	private static final String POST = "/post";
	private static final String POST_CREATE = "/post/create";
	private static final String POST_EDIT = "/post/edit";
	private static final String POST_DELETE = "/post/delete";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case POST:
			forwardToPostDetailPage(req, resp);
			break;
		case POST_CREATE:
			forwardToPostCreatePage(req, resp);
			break;
		case POST_EDIT:
			forwardToPostEditPage(req, resp);
			break;
		case POST_DELETE:
			
			break;
		default:
			break;
		}
	}

	private void forwardToPostEditPage(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	private void forwardToPostCreatePage(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	private void forwardToPostDetailPage(HttpServletRequest req, HttpServletResponse resp) {
		// get post id from request parameter
		
		// get post detail view using post id
		
		// set post detail view to request scope
		
		// show post detail page
	}
}
