package edu.ucsy.social.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({("/post/*")})
public class PostController extends Controller{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getPathInfo();
		
		if(action.equals("/create")) {
			view(req, resp, "post/create");
		}else if(action.equals("/detail")) {
			view(req, resp, "post/detail");
		}else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);

		}
	}
}
