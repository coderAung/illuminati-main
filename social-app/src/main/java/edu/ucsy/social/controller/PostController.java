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

	private void forwardToPostCreatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		view(req, resp, "post-create");
	}

	private void forwardToPostDetailPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get post id from request parameter
		if(StringTool.isEmpty(req.getParameter("postId"))) {
			// show some errors
			// forward to post not found page
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case POST:
			createPost(req, resp);
			break;
		case POST_CREATE:

			break;
		case POST_EDIT:

			break;
		case POST_DELETE:
			deletePost(req, resp);
			break;
		default:
			break;
		}
	}

	private void createPost(HttpServletRequest req, HttpServletResponse resp) {
		// get user id from login user
		
		// get from what page the request come
		
		// build a create post form object by request parameter
		
		// ask post service to create the post
		
		// return to the page where the request come
	}

	private void deletePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// get post id from request parameter
		if(null == req.getParameter("postId")) {
			redirect(req, resp, "/home");
		}
		var postId = Integer.parseInt(req.getParameter("postId"));
		
		// delete all related data with the target post
		// such as post images and post's comments
		// by using post service
		postService.deletePost(postId);
		
		// redirect to home
		redirect(req, resp, "/home");
	}

}
