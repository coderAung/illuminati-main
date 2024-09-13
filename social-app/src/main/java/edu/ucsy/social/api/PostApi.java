package edu.ucsy.social.api;

import java.io.IOException;
import java.util.Map;

import javax.sql.DataSource;

import edu.ucsy.social.service.PostService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.utils.JsonTool;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/api/post/delete" }, loadOnStartup = 1)
public class PostApi extends Api {

	private static final long serialVersionUID = 1L;

	private static final String POST_DELETE = "/api/post/delete";

	@Resource(name = "social")
	private DataSource dataSource;
	private PostService postService;

	@Override
	public void init() throws ServletException {
		postService = ServiceFactory.getService(PostService.class, dataSource);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case POST_DELETE:
			deletePost(req, resp);
			break;

		default:
			break;
		}
	}

	private void deletePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// get post id from request parameter
		if (null == req.getParameter("postId")) {

		} else {
			var postId = Integer.parseInt(req.getParameter("postId"));

			var postImages = postService.getPostView(postId).getPostImageList();
			// delete all related data with the target post
			// such as post images and post's comments
			// by using post service

			var result = postService.deletePost(postId);
			if (result) {
				if (postImages != null) {
					postImages.forEach(System.out::print);
				}
				resp.setContentType("application/json");
				var writer = resp.getWriter();
				var data = JsonTool.jsonFromMap(Map.of("result", "success"));
				writer.append(data);
				writer.flush();
			}
		}
	}

}
