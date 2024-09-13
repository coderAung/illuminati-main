package edu.ucsy.social.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import javax.sql.DataSource;

import edu.ucsy.social.service.PostImageService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.utils.DefaultPicture;
import edu.ucsy.social.utils.JsonTool;
import edu.ucsy.social.utils.StringTool;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/api/post-image/delete" })
public class PostImageApi extends Api {

	private static final long serialVersionUID = 1L;

	private static final String POST_IMAGE_DELETE = "/api/post-image/delete";

	@Resource(name = "social")
	private DataSource dataSource;
	private PostImageService postImageService;

	@Override
	public void init() throws ServletException {
		postImageService = ServiceFactory.getService(PostImageService.class, dataSource);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case POST_IMAGE_DELETE:
			deletePostImage(req, resp);
			break;
		default:
			break;
		}
	}

	private void deletePostImage(HttpServletRequest req, HttpServletResponse resp) {
		
		try(var reader = req.getReader()) {
			String line = null;
			if((line = reader.readLine()) != null) {
				var photoIds = JsonTool.fromJsonString(line, "photoIds");
				for(var photoId : photoIds) {
					var postImageName = postImageService.findPostImageById(photoId);
					if(null != postImageName) {
						var imageFolder = getServletContext().getRealPath("/photo");
						var path = Path.of(imageFolder, DefaultPicture.postFolder, postImageName);
						var result = Files.deleteIfExists(path);
						if(result) {
							result = postImageService.deletePostImageById(photoId);
							if(result) {
								resp.setContentType("application/json");
								var writer = resp.getWriter();
								var data = JsonTool.jsonFromMap(Map.of(
										"result", "success"
										));
								writer.append(data);
								writer.flush();
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	@SuppressWarnings("unused")
	private void synchronizedDeletePostImage(HttpServletRequest req, HttpServletResponse resp) {
		if (!StringTool.isEmpty(req.getParameter("postImageId"))) {
			var postImageId = Integer.parseInt(req.getParameter("postImageId"));
			var postImageName = postImageService.findPostImageById(postImageId);
			if (null != postImageName) {
				var imageFolder = getServletContext().getRealPath("/photo");
				var path = Path.of(imageFolder, DefaultPicture.postFolder, postImageName);
				try {
					var result = Files.deleteIfExists(path);
					if(result) {
						result = postImageService.deletePostImageById(postImageId);
					}
					
					if(result) {
						resp.setContentType("application/json");
						var writer = resp.getWriter();
						var data = JsonTool.jsonFromMap(Map.of(
								"result", "success"
								));
						writer.append(data);
						writer.flush();
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
