/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.security;

import java.util.Objects;

/**
 * @author issac
 *
 */
public class AuthContext {

	private Authentication authentication;

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authentication);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthContext other = (AuthContext) obj;
		return Objects.equals(authentication, other.authentication);
	}

	@Override
	public String toString() {
		return "AuthContext [authentication=" + authentication + "]";
	}

}
