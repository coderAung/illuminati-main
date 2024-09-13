package edu.ucsy.social.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.sql.DataSource;

import edu.ucsy.social.model.dto.form.PostForm;
import edu.ucsy.social.service.PostService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.utils.DefaultPicture;
import edu.ucsy.social.utils.StringTool;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/post", "/post/create", "/post/edit", "/post/comment" }, loadOnStartup = 1)
@MultipartConfig
public class PostController extends Controller {

	private static final long serialVersionUID = 1L;

	private static final String POST = "/post";
	private static final String POST_CREATE = "/post/create";
	private static final String POST_EDIT = "/post/edit";
	private static final String POST_COMMENT = "/post/comment";

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
		case POST_COMMENT:
			forwardToPostDetailPage(req, resp);
		default:
			break;
		}
	}

	private void forwardToPostEditPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// get post id from request parameter
		if (StringTool.isEmpty(req.getParameter("postId"))) {
			// redirect to profile
			redirect(req, resp, "/profile");
		} else {
			var postId = Integer.parseInt(req.getParameter("postId"));

			var postEditView = postService.getPostEditView(postId);

			if (postEditView != null) {

				if (postEditView.getUserId() == getLoginUser(req).getId()) {
					if (null != postEditView.getPostImageList()) {
						var postImages = postEditView.getPostImageList();
						postImages = postImages.stream().map(pi -> {
							var postEditImage = postEditView.new PostEditImage();
							postEditImage.setId(pi.getId());
							postEditImage.setImageName(getImagePath(pi.getImageName(), ImageType.POST));
							return postEditImage;
						}).toList();
						postEditView.setPostImageList(postImages);
					}

					req.setAttribute("postView", postEditView);
					view(req, resp, "post-edit");
				} else {
					redirect(req, resp, "/post?postId=%s".formatted(postId));
				}

			}
		}
	}

	private void forwardToPostCreatePage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		view(req, resp, "post-create");
	}

	private void forwardToPostDetailPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// get post id from request parameter
		if (StringTool.isEmpty(req.getParameter("postId"))) {
			// show some errors
			// forward to post not found page
		}
		var postId = Integer.parseInt(req.getParameter("postId"));

		// get post detail view using post id
		// get 30 comment views
		// set comment views
		// comment views are in postDetailView
		var postDetailView = postService.getPostDetailView(postId);
		if (postDetailView != null) {

			var postView = postDetailView.getPostView();
			if (null != postView.getProfileImage()) {
				postView.setProfileImage(getImagePath(postView.getProfileImage(), ImageType.PROFILE));
			} else {
				postView.setProfileImage(getImagePath(DefaultPicture.defaultProfilePicture, ImageType.PROFILE));
			}

			var postImages = postView.getPostImageList();
			if (null != postImages && postImages.size() > 0) {
				postImages = postImages.stream().map(pi -> getImagePath(pi, ImageType.POST)).toList();
			}

			postView.setPostImageList(postImages);

			var commentViews = postDetailView.getCommentViews();

			for (var cv : commentViews) {
				if (null != cv.getProfileImage()) {
					cv.setProfileImage(getImagePath(cv.getProfileImage(), ImageType.PROFILE));
				} else {
					cv.setProfileImage(getImagePath(DefaultPicture.defaultProfilePicture, ImageType.PROFILE));
				}
			}

			// set post detail view to request scope
			req.setAttribute("postDetailView", postDetailView);

			// show post detail page
			view(req, resp, "post-detail");
		} else {
			redirect(req, resp, "/home");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case POST_CREATE:
			createPost(req, resp);
			break;
		case POST_EDIT:
			editPost(req, resp);
			break;
		default:
			break;
		}
	}

	private void editPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// get user id from login user
		var userId = getLoginUser(req).getId();
		// get from what page the request come
		var content = req.getParameter("content");
		var postId = Integer.parseInt(req.getParameter("postId"));

		var parts = req.getParts().stream().skip(2).filter(part -> !part.getSubmittedFileName().equals("")).toList();

		// build a create post form object by request parameter
		var postForm = new PostForm(userId, getLoginUser(req).getName(), content);
		List<String> generatedPostImageNames = null;
		if (0 < parts.size()) {
			generatedPostImageNames = parts.stream().map(part -> StringTool
					.generateImageName(part.getSubmittedFileName(), (int) userId, getLoginUser(req).getName()))
					.toList();
			postForm.setPostImages(generatedPostImageNames);
		}
		// ask post service to edit the post
		var result = postService.editPost(postId, postForm);

		if (result) {
			if (null != generatedPostImageNames) {
				var i = 0;
				for (var part : parts) {
					var path = Path.of(getServletContext().getRealPath("photo"), "post",
							generatedPostImageNames.get(i));
					i++;
					Files.copy(part.getInputStream(), path);

				}
			}
			redirect(req, resp, "/profile");

		}
	}

	private void createPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// get user id from login user
		var userId = getLoginUser(req).getId();
		// get from what page the request come
		var content = req.getParameter("content");
		var parts = req.getParts().stream().skip(1).filter(part -> !part.getSubmittedFileName().equals("")).toList();

		// build a create post form object by request parameter
		var postForm = new PostForm(userId, getLoginUser(req).getName(), content);
		List<String> generatedPostImageNames = null;
		if (0 < parts.size()) {
			generatedPostImageNames = parts.stream().map(part -> StringTool
					.generateImageName(part.getSubmittedFileName(), (int) userId, getLoginUser(req).getName()))
					.toList();
			postForm.setPostImages(generatedPostImageNames);
		}
		// ask post service to create the post
		var result = postService.createPost(postForm);

		if (result > 0) {
			if (null != generatedPostImageNames) {
				var i = 0;
				for (var part : parts) {
					var path = Path.of(getServletContext().getRealPath("photo"), "post",
							generatedPostImageNames.get(i));
					i++;
					Files.copy(part.getInputStream(), path);

				}
			}
			redirect(req, resp, "/home");

		}
		// return to the page where the request come

	}

}
