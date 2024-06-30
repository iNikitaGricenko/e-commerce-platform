package com.wolfhack.service.user.exception.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorBody {

    private final LocalDateTime timestamp = LocalDateTime.now();

    /*@Schema(example = "400")*/ private final int status;

    /*@Schema(example = "Bad Request")*/ private final String error;

    /*@Schema(example = "/api/v1/test")*/ private final String path;

    /*@Schema(example = "Required String parameter 'name' is not present")*/ private final String message;

    public ErrorBody(HttpStatus httpStatus, WebRequest request, String message) {
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.path = ((HttpServletRequest) request).getRequestURI();
        this.message = message;
    }

}
