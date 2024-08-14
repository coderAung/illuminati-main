package edu.ucsy.social.model.entity;

import java.time.LocalDateTime;

public record User(
		long id,
		String email, 
		String name, 
		String password,
		Role role,
		Status status,
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {

	public User(String email, String name, String password) {
		this(0, email, name, password, Role.MEMBER, Status.ACTIVE, null, null);
	}
	
	public User(String email, String name, String password, Role role, Status status) {
		this(0, email, name, password, role, status, null, null);
	}

	public User perfectClone(long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
		return new User(id, email, name, password, role, status, createdAt, updatedAt);
	}
	
	public User updatedClone(LocalDateTime updatedAt) {
		return new User(id, email, name, password, role, status, createdAt, updatedAt);
	}

	public enum Role {
		ADMIN, MEMBER
	}
	
	public enum Status {
		ACTIVE, SUSPENDED
	}
}
