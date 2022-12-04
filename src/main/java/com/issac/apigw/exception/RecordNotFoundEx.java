/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.exception;

/**
 * @author issac
 *
 */
public class RecordNotFoundEx extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecordNotFoundEx(String msg) {
		super(msg);
	}
}
