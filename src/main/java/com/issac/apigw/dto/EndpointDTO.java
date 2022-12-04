/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.dto;

/**
 * @author issac
 *
 */
public class EndpointDTO {

	private String authorizationEndpoint;
	private String tokenEndpoint;
	private String jwkSetEndpoint;

	public String getAuthorizationEndpoint() {
		return authorizationEndpoint;
	}

	public void setAuthorizationEndpoint(String authorizationEndpoint) {
		this.authorizationEndpoint = authorizationEndpoint;
	}

	public String getTokenEndpoint() {
		return tokenEndpoint;
	}

	public void setTokenEndpoint(String tokenEndpoint) {
		this.tokenEndpoint = tokenEndpoint;
	}

	public String getJwkSetEndpoint() {
		return jwkSetEndpoint;
	}

	public void setJwkSetEndpoint(String jwkSetEndpoint) {
		this.jwkSetEndpoint = jwkSetEndpoint;
	}

	@Override
	public String toString() {
		return "EndpointDTO [authorizationEndpoint=" + authorizationEndpoint + ", tokenEndpoint="
				+ tokenEndpoint + ", jwkSetEndpoint=" + jwkSetEndpoint + "]";
	}

}
