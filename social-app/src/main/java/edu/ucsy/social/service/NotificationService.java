package edu.ucsy.social.service;

import java.util.List;

import edu.ucsy.social.data.Service;
import edu.ucsy.social.model.dto.view.NotificationView;

public interface NotificationService extends Service {

	List<NotificationView> getNotificationViews(long userId, int limit);

	boolean deleteNotification(String notificationId);

	boolean deleteAllNotifications(long userId);

}
