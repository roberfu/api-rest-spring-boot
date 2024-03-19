package cl.springmachine.api.library.exceptions;

import java.net.URI;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {

	private static final String RFC_STRING = "https://www.rfc-editor.org/rfc/rfc7807";

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ProblemDetail resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
		problemDetail.setType(URI.create(RFC_STRING));
		return problemDetail;
	}

	@ExceptionHandler(CustomException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ProblemDetail globalExceptionHandler(Exception ex, WebRequest request) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
				ex.getMessage());
		problemDetail.setType(URI.create(RFC_STRING));
		return problemDetail;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ProblemDetail handleException(Exception ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
				ex.getMessage());
		problemDetail.setType(URI.create(RFC_STRING));
		return problemDetail;
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ProblemDetail handleNotFoundException(NotFoundException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
		problemDetail.setType(URI.create(RFC_STRING));
		return problemDetail;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ProblemDetail handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
		String errorMessages = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.joining("; "));
		String body = "Validation error: " + errorMessages;
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, body);
		problemDetail.setType(URI.create(RFC_STRING));
		return problemDetail;
	}

}
