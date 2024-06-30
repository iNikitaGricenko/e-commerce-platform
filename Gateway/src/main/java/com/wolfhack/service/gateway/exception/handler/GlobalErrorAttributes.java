package com.wolfhack.service.gateway.exception.handler;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
		Throwable error = this.getError(request);

		Map<String, Object> attributes = super.getErrorAttributes(request, options);
		attributes.remove("timestamp");
		attributes.remove("path");
		attributes.remove("error");
		attributes.remove("requestId");
		attributes.remove("trace");
		attributes.put("message", error.getMessage());

		return attributes;
	}

}
