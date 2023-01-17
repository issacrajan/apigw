/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.security;

/**
 * @author issac
 *
 */
public interface AuthContextHolder {

	AuthContext getAuthContext();

	void setAuthContext(AuthContext authContext);

	void clearContext();

	AuthContext createAuthContext();
}
