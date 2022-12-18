/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientResponseException;

/**
 * @author issac
 *
 */
@ControllerAdvice
public class handleValEx {
	private static final Logger logger = LoggerFactory.getLogger(handleValEx.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResp> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		ErrorResp resp = new ErrorResp(HttpStatus.BAD_REQUEST, "invalid data");
		resp.setErrors(errors);
		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RestClientResponseException.class)
	public ResponseEntity<String> handleRestClientEx(RestClientResponseException ex) {
		// logger.error("Rest Client error: ", ex);

		return new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getResponseHeaders(),
				ex.getStatusCode());
	}

	@ExceptionHandler(RecordNotFoundEx.class)
	public ResponseEntity<ErrorResp> handleValidationExceptions(RecordNotFoundEx ex) {

		ErrorResp resp = new ErrorResp(HttpStatus.NOT_FOUND, ex.getMessage());
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResp> handleException(Exception ex) {
		logger.error("exception details ", ex);
		ErrorResp resp = new ErrorResp(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResp> handleRunTimeException(RuntimeException ex) {
		logger.error("runtime exception details ", ex);
		ErrorResp resp = new ErrorResp(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
