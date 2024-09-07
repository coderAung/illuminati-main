package edu.ucsy.social.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import javax.sql.DataSource;

import edu.ucsy.social.controller.Controller.ImageType;
import edu.ucsy.social.service.ImageService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.utils.JsonTool;
import edu.ucsy.social.utils.StringTool;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/photo/profile/upload",
						"/photo/cover/upload"
						}
		)
@MultipartConfig
public class PhotoUploadApi extends Api {

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "social")
	private DataSource dataSource;
	private ImageService imageService;
	
	@Override
	public void init() throws ServletException {
		imageService = ServiceFactory.getService(ImageService.class, dataSource);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		
		switch (path) {
		case "/photo/profile/upload":
			uploadProfilePhoto(req, resp);
			break;
		case "/photo/cover/upload":
			uploadCoverPhoto(req, resp);
			break;
		default:
			break;
		}
	}

	private void uploadCoverPhoto(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		var part = req.getPart("coverPhoto");
		var loginUser = getLoginUser(req);
		
		var generatedImageName = StringTool.generateImageName(part.getSubmittedFileName(), (int) loginUser.getId(), loginUser.getName());
		
		var coverImageId = imageService.saveCoverImage(loginUser.getId(), generatedImageName);
		if(coverImageId > 0) {
			var path = Path.of(getServletContext().getRealPath("photo"), "cover", generatedImageName);
			Files.copy(part.getInputStream(), path);
			
			var imagePath = getImagePath(generatedImageName, ImageType.COVER);
			
			loginUser.setCoverImage(imagePath);
			
			resp.setContentType("application/json");
			var writer = resp.getWriter();
			writer.append(JsonTool.jsonFromMap(
									Map.of(
										"result", "success",
										"imagePath", imagePath)));
			writer.flush();
		}
	}

	private void uploadProfilePhoto(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		var part = req.getPart("profilePhoto");
		var loginUser = getLoginUser(req);
		
		var generatedImageName = StringTool.generateImageName(part.getSubmittedFileName(), (int) loginUser.getId(), loginUser.getName());
		
		var profileImageId = imageService.saveProfileImage(loginUser.getId(), generatedImageName);
		if(profileImageId > 0) {
			var path = Path.of(getServletContext().getRealPath("photo"), "profile", generatedImageName);
			Files.copy(part.getInputStream(), path);

			var imagePath = getImagePath(generatedImageName, ImageType.PROFILE);
			
			loginUser.setProfileImage(imagePath);
			
			resp.setContentType("application/json");
			var writer = resp.getWriter();
			writer.append(JsonTool.jsonFromMap(
									Map.of(
										"result", "success",
										"imagePath", imagePath)));
			writer.flush();
		}
		
	}

}
