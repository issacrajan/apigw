/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

/**
 * @author issac
 *
 */
public class ErrorResp {

	private HttpStatusCode errorCode;
	private String message;
	private Map<String, String> errors;

	public ErrorResp(HttpStatusCode errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatusCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public void addError(String errField, String errMessage) {
		if (errors == null) {
			errors = new HashMap<>();
		}
		errors.put(errField, errMessage);
	}

}
