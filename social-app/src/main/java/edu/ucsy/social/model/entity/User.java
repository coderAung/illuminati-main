package edu.ucsy.social.model.entity;

import java.time.LocalDateTime;

public record User(
		long id,
		String email, 
		String name, 
		String password, 
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {

	public User(String email, String name, String password) {
		this(0, email, name, password, null, null);
	}
	
	public User perfectClone(long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
		return new User(id, email, name, password, createdAt, updatedAt);
	}

}
