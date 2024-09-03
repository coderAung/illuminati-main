package edu.ucsy.social.controller;

import java.io.IOException;

import javax.sql.DataSource;

import edu.ucsy.social.service.PostService;
import edu.ucsy.social.service.ServiceFactory;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/home"},
		loadOnStartup = 1)
public class HomeController extends Controller {

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
		// get latest random post views from database with limit 10
		var postViews = postService.getRandomPostViews(30);
		
		// set the post views to request scope
		req.setAttribute("postViews", postViews);
		view(req,resp,"home");
	}

}