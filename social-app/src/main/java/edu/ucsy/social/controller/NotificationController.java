package edu.ucsy.social.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {
				"/notifications",
				"/notifications/delete",
				"/notifications/delete-all"},
		loadOnStartup = 1)
public class NotificationController extends Controller {

	private static final long serialVersionUID = 1L;
	
	private static final String NOTIFICATIONS = "/notifications";
	private static final String NOTIFICATIONS_DELETE = "/notifications/delete";
	private static final String NOTIFICATIONS_DELETE_ALL = "/notifications/delete-all";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case NOTIFICATIONS:
			forwardToNotifiactionPage(req, resp);
			break;
		case NOTIFICATIONS_DELETE:

			break;
		case NOTIFICATIONS_DELETE_ALL:

			break;
		default:
			break;
		}
	}

	private void forwardToNotifiactionPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get user id from login user
		
		// get 30 latest notification views from database by notificationService
		
		// set notifications views to request scope
		
		// show the notifications page
		view(req, resp, "notifications");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case NOTIFICATIONS:

			break;
		case NOTIFICATIONS_DELETE:
			deleteNotification(req, resp);
			break;
		case NOTIFICATIONS_DELETE_ALL:

			break;
		default:
			break;
		}
	}

	private void deleteNotification(HttpServletRequest req, HttpServletResponse resp) {
		// get notification id from request parameter
		
		// delete notification in database by notificationService
		
		// redirect to notifications page 
	}
}
