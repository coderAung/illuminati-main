package edu.ucsy.social.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import edu.ucsy.social.controller.Controller.ImageType;
import edu.ucsy.social.model.dto.form.CommentForm;
import edu.ucsy.social.service.CommentService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.utils.DefaultPicture;
import edu.ucsy.social.utils.JsonTool;
import edu.ucsy.social.utils.StringTool;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/api/comment", 
						"/api/comment/create",
						"/api/comment/update",
						"/api/comment/delete"},
		loadOnStartup = 1)
public class CommentApi extends Api {

	private static final long serialVersionUID = 1L;

	private static final String COMMENT = "/api/comment";
	private static final String COMMENT_CREATE = "/api/comment/create";
	private static final String COMMENT_UPDATE = "/api/comment/update";
	private static final String COMMENT_DELETE = "/api/comment/delete";
	
	@Resource(name = "social")
	private DataSource dataSource;
	private CommentService commentService;
	
	@Override
	public void init() throws ServletException {
		commentService = ServiceFactory.getService(CommentService.class, dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case COMMENT:
			getComments(req, resp);
			break;
		default:
			break;
		}
	}
	
	private void getComments(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// get post id and last comment id from request parameter
		if(StringTool.isEmpty(req.getParameter("postId")) ||
				StringTool.isEmpty(req.getParameter("lastCommentId"))) {
			// fail
		}
		var postId = Integer.parseInt(req.getParameter("postId"));
		var lastCommentId = Integer.parseInt(req.getParameter("lastCommentId"));
		// get limit 10 comment views from database where id is less than comment id
		// in descending order
		var commentViews = commentService.getCommentViews(postId, lastCommentId, 10);
		// and respond it as comment view json to front end
		resp.setContentType("application/json");
		var writer = resp.getWriter();
		if(commentViews != null) {
			var jsonResponse = JsonTool.jsonFromMap(Map.of(
					"status", "success",
					"data", List.of(commentViews),
					"lastCommentId", commentViews.get(commentViews.size() - 1).getId()
					));
			writer.write(jsonResponse);
		} else {
			var jsonResponse = JsonTool.jsonFromMap(Map.of(
					"status", "fail",
					"data", 0,
					"lastCommentId", 0
					));
			writer.write(jsonResponse);
		}
		writer.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case COMMENT_CREATE:
			createComment(req, resp);
			break;
		case COMMENT_UPDATE:
			updateComment(req, resp);
			break;
		case COMMENT_DELETE:
			deleteComment(req, resp);
			break;
		default:
			break;
		}
	}
	
	private void updateComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// get form data from request parameter
		// postId, userId, commentContent
		if(StringTool.isEmpty(req.getParameter("postId")) ||
				StringTool.isEmpty(req.getParameter("commentContent"))) {
			// fail
		}
		
		var postId = Integer.parseInt(req.getParameter("postId"));
		var loginUser = getLoginUser(req);
		var userId = loginUser.getId();
		var userName = loginUser.getName();
		var commentContent = req.getParameter("commentContent");

		// create comment form object
		var commentForm = new CommentForm(postId, userId, userName, commentContent);
		// ask comment service to create comment using above form
		var commentView = commentService.updateComment(commentForm);

		// respond comment view of the created comment as json
		resp.setContentType("application/json");
		if(null != commentView) {
			var writer = resp.getWriter();
			writer.write(JsonTool.jsonFromSingleObject(commentView));
			writer.flush();
		}
	}

	private void deleteComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// get comment id from request parameter
		if(StringTool.isEmpty(req.getParameter("commentId"))) {
			// fail
		}
		var commentId = Integer.parseInt(req.getParameter("commentId"));
		// delete the comment using comment id by commentService
		var result = commentService.deleteComment(commentId);
		
		if(result) {
			// respond success or fail message to front end
			resp.setContentType("application/json");
			var writer = resp.getWriter();
			var data = JsonTool.jsonFromMap(Map.of(
					"result", "success",
					"commentId", commentId
					));
			writer.write(data);
			writer.flush();
		}
	}

	private void createComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// get form data from request parameter
		// postId, userId, commentContent
		if(StringTool.isEmpty(req.getParameter("postId")) ||
				StringTool.isEmpty(req.getParameter("commentContent"))) {
			// fail
		}
		
		var loginUser = getLoginUser(req);
		
		var postId = Integer.parseInt(req.getParameter("postId"));
		var userId = loginUser.getId();
		var userName = loginUser.getName();
		var commentContent = req.getParameter("commentContent");

		// create comment form object
		var commentForm = new CommentForm(postId, userId, userName, commentContent);
		// ask comment service to create comment using above form
		var commentView = commentService.createComment(commentForm);

		// respond comment view of the created comment as json
		resp.setContentType("application/json");
		if(null != commentView) {
			
			if(null != commentView.getProfileImage()) {
				commentView.setProfileImage(getImagePath(commentView.getProfileImage(), ImageType.PROFILE));
			} else {
				commentView.setProfileImage(getImagePath(DefaultPicture.defaultProfilePicture, ImageType.PROFILE));
			}
			
			var writer = resp.getWriter();
			var data = JsonTool.jsonFromMap(Map.of(
					"result", "success",
					"commentView", commentView
					));
			writer.write(data);
			writer.flush();
		}
		
	}
	
	@SuppressWarnings("unused")
	private void commentTest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
	}
	
	@SuppressWarnings("unused")
	private void oneCommentTest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		// test comment
		var writer = resp.getWriter();
	}

}
