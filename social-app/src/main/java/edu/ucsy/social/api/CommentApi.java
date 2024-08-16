package edu.ucsy.social.api;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import edu.ucsy.social.model.dto.form.CommentForm;
import edu.ucsy.social.model.dto.view.CommentView;
import edu.ucsy.social.service.CommentService;
import edu.ucsy.social.service.ServiceFactory;
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
			// testing
//			var c1 = new CommentView(1, "some comment", "ywa", LocalDateTime.now());
//			var c2 = new CommentView(2, "some comment", "ywa", LocalDateTime.now());
//			var c3 = new CommentView(3, "some comment", "ywa", LocalDateTime.now());
//			var c4 = new CommentView(4, "some comment", "ywa", LocalDateTime.now());
//			var commentViews = List.of(c1, c2, c3, c4);
//			// and respond it as comment view json to front end
//			var jsonCommentViews = JsonTool.jsonFromList(List.of(commentViews));
//			var jsonResponse = JsonTool.jsonFromMap(Map.of(
//											"status", "success",
//											"data", List.of(commentViews),
//											"empty", 0
//											));
//			resp.setContentType("application/json");
//			var writer = resp.getWriter();
//			writer.write(jsonResponse);
			// end testing
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
				StringTool.isEmpty(req.getParameter("userId")) ||
				StringTool.isEmpty(req.getParameter("comment"))) {
			// fail
		}
		
		var postId = Integer.parseInt(req.getParameter("postId"));
		var userId = Integer.parseInt(req.getParameter("userId"));
		var commentContent = req.getParameter("comment");

		// create comment form object
		var commentForm = new CommentForm(postId, userId, commentContent);
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
		
		// respond success or fail message to front end
		resp.setContentType("application/json");
		var writer = resp.getWriter();
		writer.write(JsonTool.jsonFromSingleObject(result));
		writer.flush();
	}

	private void createComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// get form data from request parameter
		// postId, userId, commentContent
		if(StringTool.isEmpty(req.getParameter("postId")) ||
				StringTool.isEmpty(req.getParameter("userId")) ||
				StringTool.isEmpty(req.getParameter("comment"))) {
			// fail
		}
		
		var postId = Integer.parseInt(req.getParameter("postId"));
		var userId = Integer.parseInt(req.getParameter("userId"));
		var commentContent = req.getParameter("comment");

		// create comment form object
		var commentForm = new CommentForm(postId, userId, commentContent);
		// ask comment service to create comment using above form
		var commentView = commentService.createComment(commentForm);

		// respond comment view of the created comment as json
		resp.setContentType("application/json");
		if(null != commentView) {
			var writer = resp.getWriter();
			writer.write(JsonTool.jsonFromSingleObject(commentView));
			writer.flush();
		}
		
	}
	
	@SuppressWarnings("unused")
	private void commentTest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		// test comment
		var c1 = new CommentView(1, "some comment", "ywa", LocalDateTime.now());
		var c2 = new CommentView(2, "some comment", "ywa", LocalDateTime.now());
		var c3 = new CommentView(3, "some comment", "ywa", LocalDateTime.now());
		var c4 = new CommentView(4, "some comment", "ywa", LocalDateTime.now());
		var c5 = new CommentView(5, "some comment", "ywa", LocalDateTime.now());
		
		var writer = resp.getWriter();
		writer.write(JsonTool.jsonFromList(List.of(c1, c2, c3, c4, c5)));
	}
	
	@SuppressWarnings("unused")
	private void oneCommentTest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		// test comment
		var c1 = new CommentView(1, "some comment", "ywa", LocalDateTime.now());
		var writer = resp.getWriter();
		writer.write(JsonTool.jsonFromMap(Map.of("comment", c1)));
	}

}
