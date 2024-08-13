package edu.ucsy.social.model.dto;

import java.io.Serializable;

import edu.ucsy.social.model.entity.User.Role;

public class LoginUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String email;
	private String name;
	private Role role;

	public LoginUser(int id, String email, String name, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
