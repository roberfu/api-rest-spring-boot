package cl.springmachine.api.library.exceptions;

import java.io.Serial;

public class CustomException extends Exception {

	@Serial
	private static final long serialVersionUID = 1L;

	public CustomException(String message) {
		super(message);
	}
}
