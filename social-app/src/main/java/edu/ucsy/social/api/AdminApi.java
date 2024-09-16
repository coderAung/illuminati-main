package edu.ucsy.social.api;

import java.io.IOException;
import java.util.Map;

import javax.sql.DataSource;

import edu.ucsy.social.route.AdminRoute;
import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.service.UserService;
import edu.ucsy.social.utils.JsonTool;
import edu.ucsy.social.utils.StringTool;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { AdminRoute.ACTIVATE_USER, AdminRoute.SUSPEND_USER }, loadOnStartup = 1)
public class AdminApi extends Api {

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "social")
	private DataSource dataSource;
	
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = ServiceFactory.getService(UserService.class, dataSource);
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case AdminRoute.ACTIVATE_USER:
			activateUser(req, resp);
			break;
		case AdminRoute.SUSPEND_USER:
			suspendUser(req, resp);
			break;
		default:
			break;
		}
	}

	private void suspendUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		if (!StringTool.isEmpty(req.getParameter("userId"))) {
			var userId = Integer.parseInt(req.getParameter("userId"));
			var result = userService.suspendUser(userId);
			if(result) {
				var writer = resp.getWriter();
				var data = JsonTool.jsonFromMap(Map.of(
						"result", "success"
						));
				writer.write(data);
				writer.flush();
			}
		}
	}

	private void activateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (!StringTool.isEmpty(req.getParameter("userId"))) {
			var userId = Integer.parseInt(req.getParameter("userId"));
			var result = userService.activateUser(userId);
			if(result) {
				var writer = resp.getWriter();
				var data = JsonTool.jsonFromMap(Map.of(
						"result", "success"
						));
				writer.write(data);
				writer.flush();
			}
		}
	}

}
