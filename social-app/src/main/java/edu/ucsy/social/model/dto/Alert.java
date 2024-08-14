package edu.ucsy.social.model.dto;

public class Alert {

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
