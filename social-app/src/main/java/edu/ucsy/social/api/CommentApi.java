package edu.ucsy.social.api;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import edu.ucsy.social.model.dto.view.CommentView;
import edu.ucsy.social.utils.JsonTool;
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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case COMMENT:
			
			break;
		case COMMENT_CREATE:
			createAComment(req, resp);
			break;
		case COMMENT_UPDATE:
			
			break;
		case COMMENT_DELETE:
			
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case COMMENT:
			
			break;
		case COMMENT_CREATE:
			createAComment(req, resp);
			break;
		case COMMENT_UPDATE:
			
			break;
		case COMMENT_DELETE:
			
			break;
		default:
			break;
		}
	}
	
	private void createAComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		// get form data from request parameter
		
		// create comment form object
		
		// ask comment service to create comment using above form
		
		// respond comment view of the created comment as json
		
		// test comment
		var c1 = new CommentView(1, "some comment", "ywa", LocalDateTime.now());
		var c2 = new CommentView(2, "some comment", "ywa", LocalDateTime.now());
		var c3 = new CommentView(3, "some comment", "ywa", LocalDateTime.now());
		var c4 = new CommentView(4, "some comment", "ywa", LocalDateTime.now());
		var c5 = new CommentView(5, "some comment", "ywa", LocalDateTime.now());
		
		var writer = resp.getWriter();
		writer.write(JsonTool.jsonFromList(List.of(c1, c2, c3, c4, c5)));
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case COMMENT:
			
			break;
		case COMMENT_CREATE:
			
			break;
		case COMMENT_UPDATE:
			
			break;
		case COMMENT_DELETE:
			
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case COMMENT:
			
			break;
		case COMMENT_CREATE:
			
			break;
		case COMMENT_UPDATE:
			
			break;
		case COMMENT_DELETE:
			
			break;
		default:
			break;
		}
	}
}
