package edu.ucsy.social.controller;

import java.io.IOException;

import javax.sql.DataSource;

import edu.ucsy.social.service.NotificationService;
import edu.ucsy.social.service.ServiceFactory;
import jakarta.annotation.Resource;
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

	@Resource(name = "social")
	private DataSource dataSource;
	private NotificationService notificationService;
	
	@Override
	public void init() throws ServletException {
		notificationService = ServiceFactory.getService(NotificationService.class, dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		switch (path) {
		case NOTIFICATIONS:
			forwardToNotifiactionPage(req, resp);
			break;
		case NOTIFICATIONS_DELETE:
			redirect(req, resp, NOTIFICATIONS);
			break;
		case NOTIFICATIONS_DELETE_ALL:
			redirect(req, resp, NOTIFICATIONS);
			break;
		default:
			break;
		}
	}

	private void forwardToNotifiactionPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get user id from login user
		var userId = getLoginUser(req).getId();
		// get 30 latest notification views from database by notificationService
		var notificationViews = notificationService.getNotificationViews(userId, 30);
		// set notifications views to request scope
		req.setAttribute("notificationViews", notificationViews);
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
			deleteAllNotification(req, resp);
			break;
		default:
			break;
		}
	}

	private void deleteAllNotification(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// get user id from request parameter
		var userId = getLoginUser(req).getId();
		// delete all notification of the user in database
		notificationService.deleteAllNotifications(userId);
		// redirect to notification page
		redirect(req, resp, NOTIFICATIONS);
	}

	private void deleteNotification(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// get notification id from request parameter
		var notificationId = req.getParameter("notificationId");
		// delete notification in database by notificationService
		notificationService.deleteNotification(notificationId);
		// redirect to notifications page 
		redirect(req, resp, NOTIFICATIONS);
	}
}
