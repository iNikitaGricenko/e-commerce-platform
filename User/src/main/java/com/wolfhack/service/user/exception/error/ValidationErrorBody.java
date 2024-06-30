package com.wolfhack.service.user.exception.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorBody {

	private final LocalDateTime timestamp = LocalDateTime.now();

	/*@Schema(example = "400")*/ private final int status;

	/*@Schema(example = "Bad Request")*/ private final String error;

	/*@Schema(example = "/api/v1/test")*/ private final String path;

	private final String[] errors;

	public ValidationErrorBody(HttpStatus httpStatus, WebRequest request, List<String> messages) {
		this.status = httpStatus.value();
		this.error = httpStatus.getReasonPhrase();
		this.path = ((HttpServletRequest) request).getRequestURI();
		this.errors = messages.toArray(new String[0]);
	}

}
