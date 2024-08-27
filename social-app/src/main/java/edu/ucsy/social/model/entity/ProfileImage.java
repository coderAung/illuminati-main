package edu.ucsy.social.model.entity;

import java.time.LocalDateTime;

import edu.ucsy.social.model.entity.type.ImageStatus;

public record ProfileImage(
		long id,
		String name,
		long userId,
		ImageStatus status,
		LocalDateTime uploadedAt) {

	public ProfileImage(String name, long userId) {
		this(0, name, userId, ImageStatus.ACTIVE, null);
	}
	
	public ProfileImage perfectClone(long id, LocalDateTime uploadedAt) {
		return new ProfileImage(id, name, userId, status, uploadedAt);
	}
}
