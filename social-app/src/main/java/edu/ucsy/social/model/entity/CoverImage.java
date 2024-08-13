package edu.ucsy.social.model.entity;

import java.time.LocalDateTime;

import edu.ucsy.social.model.entity.type.ImageStatus;

public record CoverImage(
		long id,
		String name,
		long userId,
		ImageStatus status,
		LocalDateTime uploadedAt) {

	public CoverImage(String name, long userId) {
		this(0, name, userId, ImageStatus.ACTIVE, null);
	}
	
	public CoverImage perfectClone(long id, LocalDateTime uploadedAt) {
		return new CoverImage(id, name, userId, status, uploadedAt);
	}
}
