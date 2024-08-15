package edu.ucsy.social.data.exception;

public class NoSuchModelException extends DataException {

	private static final long serialVersionUID = 1L;

	public NoSuchModelException(Class<?> e) {
		super("No Suitable Model for Entity %s was found".formatted(e.getSimpleName()));
	}
}
