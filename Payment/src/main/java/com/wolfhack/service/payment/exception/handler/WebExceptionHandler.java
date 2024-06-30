package com.wolfhack.service.payment.exception.handler;

import com.wolfhack.common.exception.BadRequestException;
import com.wolfhack.common.exception.ForbiddenException;
import com.wolfhack.common.exception.NotFoundException;
import com.wolfhack.common.exception.error.ErrorBody;
import com.wolfhack.common.exception.error.ValidationErrorBody;
import jakarta.validation.ValidationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException exception,
		HttpHeaders headers,
		HttpStatusCode status,
		WebRequest request
	) {
		List<String> errors = exception
			.getBindingResult()
			.getAllErrors()
			.stream()
			.map(ObjectError::getDefaultMessage)
			.collect(toList());

		ValidationErrorBody body = new ValidationErrorBody(
			HttpStatus.valueOf(status.value()),
			request, errors
		);

		return handleExceptionInternal(exception, body, headers, status, request);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Object> handleViolationAccess(ValidationException exception, WebRequest request) {
		return handleException(exception, request, BAD_REQUEST);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handleBadRequestException(BadRequestException exception, WebRequest request) {
		return handleException(exception, request, BAD_REQUEST);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<Object> handleForbiddenException(ForbiddenException exception, WebRequest request) {
		return handleException(exception, request, FORBIDDEN);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(NotFoundException exception, WebRequest request) {
		return handleException(exception, request, NOT_FOUND);
	}

	private ResponseEntity<Object> handleException(Exception exception, WebRequest request, HttpStatus status) {
		HttpHeaders headers = new HttpHeaders();
		ErrorBody body = new ErrorBody(status, request, exception.getMessage());
		return handleExceptionInternal(exception, body, headers, status, request);
	}
}
