package edu.ucsy.social.data.exception;

public class IllegalRelationshipException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalRelationshipException(Class<?> e1, Class<?> e2) {
		super("There is no relation between '%s' and '%s'.".formatted(e1.getSimpleName(), e2.getSimpleName()));
	}
}
