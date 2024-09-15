package edu.ucsy.social.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import javax.sql.DataSource;

import edu.ucsy.social.model.dto.form.SavePostForm;
import edu.ucsy.social.service.PostService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.utils.DefaultPicture;
import edu.ucsy.social.utils.JsonTool;
import edu.ucsy.social.utils.StringTool;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/api/post/delete", "/api/post/save", "/api/post/unsave" }, loadOnStartup = 1)
public class PostApi extends Api {

	private static final long serialVersionUID = 1L;

	private static final String POST_DELETE = "/api/post/delete";
	private static final String POST_SAVE = "/api/post/save";
	private static final String POST_UNSAVE = "/api/post/unsave";

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
		case POST_SAVE:
			savePost(req, resp);
			break;
		case POST_UNSAVE:
			unsavePost(req, resp);
			break;
		default:
			break;
		}
	}

	private void unsavePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if(!StringTool.isEmpty(req.getParameter("postId"))) {
			// get data from request parameter
			var postId = Integer.parseInt(req.getParameter("postId"));
			var loginUser = getLoginUser(req);
			
			var userId = loginUser.getId();
			// create a save post form
			var savePostForm = new SavePostForm(userId, postId);
			// ask post service to save post
			var result = postService.unsaveThePost(savePostForm);
			
			if(result) {
				// send success response
				var writer = resp.getWriter();
				var data = JsonTool.jsonFromMap(Map.of("result", "success"));
				writer.append(data);
				writer.flush();
			}
		}
	}

	private void savePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if(!StringTool.isEmpty(req.getParameter("postId"))) {
			// get data from request parameter
			var postId = Integer.parseInt(req.getParameter("postId"));
			var loginUser = getLoginUser(req);
			
			var userId = loginUser.getId();
			// create a save post form
			var savePostForm = new SavePostForm(userId, postId);
			// ask post service to save post
			var result = postService.saveThePost(savePostForm);
			
			if(result) {
				// send success response
				var writer = resp.getWriter();
				var data = JsonTool.jsonFromMap(Map.of("result", "success"));
				writer.append(data);
				writer.flush();
			}
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
					var imageFolder = getServletContext().getRealPath("/photo");
					for(var pi : postImages) {
						var path = Path.of(imageFolder, DefaultPicture.postFolder, pi);
						Files.deleteIfExists(path);
					}
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
