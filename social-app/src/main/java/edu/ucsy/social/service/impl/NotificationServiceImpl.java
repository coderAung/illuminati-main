package edu.ucsy.social.service.impl;

import java.sql.Connection;
import java.util.List;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.dto.view.NotificationView;
import edu.ucsy.social.model.entity.Notification;
import edu.ucsy.social.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {

	@SuppressWarnings("unused")
	private DatabaseConnector connector;

	private Model<Notification> notificationModel;
	
	public NotificationServiceImpl(DatabaseConnector connector) {
		this.connector = connector;
		this.notificationModel = ModelFactory.getModel(Notification.class);
	}
	
	@Override
	public void initConnection(Connection connection) {
		notificationModel.setConnection(connection);
	}

	@Override
	public void destroyConnection() {
		notificationModel.setConnection(null);
	}

	@Override
	public List<NotificationView> getNotificationViews(long userId, int limit) {
		
		return null;
	}

	@Override
	public boolean deleteNotification(String notificationId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAllNotifications(long userId) {
		// TODO Auto-generated method stub
		return false;
	}

}
