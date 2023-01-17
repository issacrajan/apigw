/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.security;

import java.util.List;

/**
 * @author issac
 *
 */
public class Authentication {
	private String userId;
	private String email;
	private List<String> roles;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Authentication [userId=" + userId + ", email=" + email + ", roles=" + roles + "]";
	}

}
