package edu.ucsy.social.controller;

import java.io.IOException;

import javax.sql.DataSource;

import edu.ucsy.social.service.PostService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.utils.StringTool;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/post/comment"},
		loadOnStartup = 1)
public class CommentController extends Controller {

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "social")
	private DataSource dataSource;
	private PostService postService;
	
	@Override
	public void init() throws ServletException {
		postService = ServiceFactory.getService(PostService.class, dataSource);
	}

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
		// get post id from request parameter
		if(StringTool.isEmpty(req.getParameter("postId"))) {
			// show some errors
			// redirect to post not found page

		}
		var postId = Integer.parseInt(req.getParameter("postId"));

		// get post detail view using post id
		// get 30 comment views
		// set comment views
		// comment views are in postDetailView
		var postDetailView = postService.getPostDetailView(postId);
		
		// set post detail view to request scope
		req.setAttribute("postDetailView", postDetailView);
		
		// show post detail page
		view(req, resp, "post-detail");
	}

}
