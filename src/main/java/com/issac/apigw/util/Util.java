/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.util;

/**
 * @author issac
 *
 */
public class Util {

	public static boolean isBlank(String str) {
		return str == null || str.isBlank();
	}

	public static boolean hasText(String str) {
		return str != null && !str.isBlank();
	}
}
