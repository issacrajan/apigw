/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.dto;

/**
 * @author issac
 *
 */
public class ErrorMsgDTO {

	private int errorCode;
	private String message;

	public ErrorMsgDTO(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorMsgDTO [errorCode=" + errorCode + ", message=" + message + "]";
	}

}
