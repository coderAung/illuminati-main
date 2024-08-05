package edu.ucsy.social.model.entity;

import java.time.LocalDateTime;

public record ProfileImage(
		long id,
		String name,
		long userId,
		LocalDateTime uploadedAt) {

	public ProfileImage(String name, long userId) {
		this(0, name, userId, null);
	}
	
	public ProfileImage perfectClone(long id, LocalDateTime uploadedAt) {
		return new ProfileImage(id, name, userId, uploadedAt);
	}
}
