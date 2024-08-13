package edu.ucsy.social.model.entity;

import java.time.LocalDateTime;

public record User(
		long id,
		String email, 
		String name, 
		String password,
		Role role,
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {

	public User(String email, String name, String password) {
		this(0, email, name, password, Role.MEMBER, null, null);
	}
	
	public User(String email, String name, String password, Role role) {
		this(0, email, name, password, role, null, null);
	}

	public User perfectClone(long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
		return new User(id, email, name, password, role, createdAt, updatedAt);
	}
	
	public User updatedClone(LocalDateTime updatedAt) {
		return new User(id, email, name, password, role, createdAt, updatedAt);
	}

	public enum Role {
		ADMIN, MEMBER
	}
}
