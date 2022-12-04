/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.dto;

import java.time.Instant;

import com.issac.apigw.entity.RegisteredClientEntity;

/**
 * @author issac
 *
 */
public class RegisteredClientDTO {

	private String id;
	private String clientId;
	private String clientName;
	private String clientSecret;
	private Instant clientIdIssuedAt;
	private Instant clientSecretExpiresAt;
	private String clientAuthenticationMethods;
	private String authorizationGrantTypes;
	private String redirect_uris;

	public static RegisteredClientDTO fromEntity(RegisteredClientEntity e) {
		RegisteredClientDTO dto = new RegisteredClientDTO();
		dto.setId(e.getId());
		dto.setClientId(e.getClientId());
		dto.setClientName(e.getClientName());
		dto.setClientSecret(e.getClientSecret());
		dto.setRedirect_uris(e.getRedirect_uris());
		return dto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Instant getClientIdIssuedAt() {
		return clientIdIssuedAt;
	}

	public void setClientIdIssuedAt(Instant clientIdIssuedAt) {
		this.clientIdIssuedAt = clientIdIssuedAt;
	}

	public Instant getClientSecretExpiresAt() {
		return clientSecretExpiresAt;
	}

	public void setClientSecretExpiresAt(Instant clientSecretExpiresAt) {
		this.clientSecretExpiresAt = clientSecretExpiresAt;
	}

	public String getClientAuthenticationMethods() {
		return clientAuthenticationMethods;
	}

	public void setClientAuthenticationMethods(String clientAuthenticationMethods) {
		this.clientAuthenticationMethods = clientAuthenticationMethods;
	}

	public String getAuthorizationGrantTypes() {
		return authorizationGrantTypes;
	}

	public void setAuthorizationGrantTypes(String authorizationGrantTypes) {
		this.authorizationGrantTypes = authorizationGrantTypes;
	}

	public String getRedirect_uris() {
		return redirect_uris;
	}

	public void setRedirect_uris(String redirect_uris) {
		this.redirect_uris = redirect_uris;
	}

	@Override
	public String toString() {
		return "RegisteredClientDTO [id=" + id + ", clientId=" + clientId + ", clientName=" + clientName
				+ ", clientSecret=" + clientSecret + ", clientIdIssuedAt=" + clientIdIssuedAt
				+ ", clientSecretExpiresAt=" + clientSecretExpiresAt + ", clientAuthenticationMethods="
				+ clientAuthenticationMethods + ", authorizationGrantTypes=" + authorizationGrantTypes
				+ ", redirect_uris=" + redirect_uris + "]";
	}

}
