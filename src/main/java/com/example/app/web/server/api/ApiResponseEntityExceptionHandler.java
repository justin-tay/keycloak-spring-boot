package com.example.app.web.server.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * A {@link ResponseEntityExceptionHandler}.
 */
@ControllerAdvice
public class ApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RestClientResponseException.class)
	public ResponseEntity<Object> handleWebClientResponseException(RestClientResponseException ex, WebRequest request) {
		return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
	}

}