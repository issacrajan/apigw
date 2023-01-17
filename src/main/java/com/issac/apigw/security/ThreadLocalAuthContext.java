/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.security;

/**
 * @author issac
 *
 */
public class ThreadLocalAuthContext implements AuthContextHolder {
	private static final ThreadLocal<AuthContext> contextHolder = new ThreadLocal<>();

	@Override
	public AuthContext getAuthContext() {
		return contextHolder.get();
	}

	@Override
	public void setAuthContext(AuthContext authContext) {
		contextHolder.set(authContext);
	}

	@Override
	public void clearContext() {
		contextHolder.remove();
	}

	@Override
	public AuthContext createAuthContext() {
		return new AuthContext();
	}

}
