package edu.ucsy.social.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {
				"/profile",
				"/profile/friends",
				"/profile/detail",
				"/profile/edit"},
		loadOnStartup = 1)
public class ProfileController extends Controller {

	private static final long serialVersionUID = 1L;
	private static final String PROFILE = "/profile";
	private static final String PROFILE_FRIENDS = "/profile/friends";
	private static final String PROFILE_DETAIL = "/profile/detail";
	private static final String PROFILE_EDIT = "/profile/edit";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		
		switch (path) {
		case PROFILE:
			forwardToProfilePage(req, resp);
			break;
		case PROFILE_FRIENDS:
			forwardToFriendPage(req, resp);
			break;
		case PROFILE_DETAIL:
			forwardToProfileDetailPage(req, resp);
			break;
		case PROFILE_EDIT:
			forwardToProfileEditPage(req, resp);
			break;
		default:
			break;
		}
		
	}

	private void forwardToProfileEditPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		view(req, resp, "profile-edit");
	}

	private void forwardToProfileDetailPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		view(req, resp, "profile-detail");
	}

	private void forwardToFriendPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		view(req, resp, "friends");
	}

	private void forwardToProfilePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		view(req, resp, "profile");
	}

}
