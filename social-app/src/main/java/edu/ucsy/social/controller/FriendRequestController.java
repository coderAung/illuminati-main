package edu.ucsy.social.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/friend-requests"}
		)
public class FriendRequestController extends Controller {

	private static final long serialVersionUID = 1L;
	
	private static final String FRIEND_REQUEST =  "/friend-requests";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case FRIEND_REQUEST:
			forwardToFriendRequestPage(req, resp);
			break;
		default:
			break;
		}
	}

	private void forwardToFriendRequestPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		view(req, resp, "friend-request");
	}
}
