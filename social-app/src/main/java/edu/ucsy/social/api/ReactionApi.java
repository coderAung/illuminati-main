package edu.ucsy.social.api;

import java.io.IOException;
import java.util.Map;

import javax.sql.DataSource;

import edu.ucsy.social.service.ReactionService;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.utils.JsonTool;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/api/reaction/create", "/api/reation/delete"})
public class ReactionApi extends Api {

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "social")
	private DataSource dataSource;
	private ReactionService reactionService;
	
	@Override
	public void init() throws ServletException {
		reactionService = ServiceFactory.getService(ReactionService.class, dataSource);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		if(path.equals("/api/reaction/create")) {
			createReaction(req, resp);
		} else if(path.equals("/api/reaction/delete")) {
			
		}
	}

	private void createReaction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		var loginUser = getLoginUser(req);
		var userId = loginUser.getId();
		var postId = Integer.parseInt(req.getParameter("postId"));
		var result = reactionService.createReaction(userId, postId);
		
		if(result) {
			var reactionCount = reactionService.getReactionCount(postId);
			resp.setContentType("application/json");
			var writer = resp.getWriter();
			var data = JsonTool.jsonFromMap(Map.of("result", "success", "count", reactionCount));
			writer.append(data);
			writer.flush();

		}
	}

}
