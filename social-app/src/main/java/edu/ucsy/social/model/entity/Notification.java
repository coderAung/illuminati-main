package edu.ucsy.social.model.entity;

import java.time.LocalDateTime;

import edu.ucsy.social.data.annotation.Entity;

@Entity
public record Notification(
		long id,
		String message,
		NotificationType type,
		NotificationStatus status,
		LocalDateTime notifiedAt,
		long userId,
		long targetId
		) {
	
	private Notification(String message, NotificationType type, long userId, long targetId) {
		this(0, message, type, NotificationStatus.UNCHECKED, null, userId, targetId);
	}
	
	public static Notification getFriendRequestNotification(String requestFromUser, long requestFromUserId, long userId) {
		return new Notification(
				generateFriendRequestMessage(requestFromUser), 
				NotificationType.FriendRequest, 
				userId,
				requestFromUserId);
	}
	
	public static Notification getNewCommentNotification(String commentFromUser, long commentFromUserId, long userId) {
		return new Notification(
				generateNewCommentMessage(commentFromUser), 
				NotificationType.NewComment, 
				userId,
				commentFromUserId);
	}
	
	private static String generateFriendRequestMessage(String requestFromUser) {
		return "You have a friend request from %s".formatted(requestFromUser);
	}

	private static String generateNewCommentMessage(String commentFromUser) {
		return "You have a friend request from %s".formatted(commentFromUser);
	}

	public enum NotificationStatus {
		CHECKED, UNCHECKED
	}
	
	public enum NotificationType {
		FriendRequest, NewComment
	}
	
	public Notification perfectClone(long id, LocalDateTime notifiedAt) {
		return new Notification(id, message, type, status, notifiedAt, userId, targetId);
	}
}
