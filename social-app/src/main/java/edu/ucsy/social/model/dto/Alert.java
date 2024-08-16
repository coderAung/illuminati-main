package edu.ucsy.social.model.dto;

import java.io.Serializable;

public class Alert implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	private AlertType alertType;

	public Alert(String message, AlertType alertType) {
		super();
		this.message = message;
		this.alertType = alertType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AlertType getAlertType() {
		return alertType;
	}

	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}

	public enum AlertType {
		SUCCESS, INFO, WARNING, DANGER, NORMAL
	}
}
