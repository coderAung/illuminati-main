package edu.ucsy.social.data.exception;

public class IllegalEntityException extends DataException {

	private static final long serialVersionUID = 1L;

	public IllegalEntityException(Class<?> e) {
		super("%s is not an valid entity.".formatted(e.getSimpleName()));
	}

}
