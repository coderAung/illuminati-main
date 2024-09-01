package edu.ucsy.social.model.dto.form;

import java.io.Serializable;

public class RegisterForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String password;
	private String confirmPassword;
	
	public RegisterForm(String name, String email, String password, String confirmPassword) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
