package edu.ucsy.social.api;

import java.io.IOException;
import java.util.Map;

import javax.sql.DataSource;

import edu.ucsy.social.service.ServiceFactory;
import edu.ucsy.social.service.UserService;
import edu.ucsy.social.utils.JsonTool;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/api/authenticate"}, loadOnStartup = 1)
public class AuthenticationApi extends Api {
	
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "social")
	private DataSource dataSource;
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = ServiceFactory.getService(UserService.class, dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var loginUser = getLoginUser(req);
		var status = userService.checkStatus(loginUser.getId());
		loginUser.setStatus(status);
		
		resp.setContentType("application/json");
		var writer = resp.getWriter();
		var data = JsonTool.jsonFromMap(Map.of("result", "done"));
		writer.append(data);
		writer.flush();

	}
}
