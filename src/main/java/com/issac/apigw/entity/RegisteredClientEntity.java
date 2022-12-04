/**
 * 
 */
package com.issac.apigw.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author issac
 *
 */
@Entity
@Table(name = "oauth2_registered_client")
public class RegisteredClientEntity {

	@Id
	private String id;
	private String clientId;
	private String clientName;
	private String clientSecret;
	private Instant clientIdIssuedAt;
	private Instant clientSecretExpiresAt;
	private String clientAuthenticationMethods;
	private String authorizationGrantTypes;
	private String redirect_uris;
	private String scopes;
	private String clientSettings;
	private String tokenSettings;
	private Instant createdTs;
	private Instant updatedTs;
	private String createdBy;
	private String updatedBy;

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

	public String getScopes() {
		return scopes;
	}

	public void setScopes(String scopes) {
		this.scopes = scopes;
	}

	public String getClientSettings() {
		return clientSettings;
	}

	public void setClientSettings(String clientSettings) {
		this.clientSettings = clientSettings;
	}

	public String getTokenSettings() {
		return tokenSettings;
	}

	public void setTokenSettings(String tokenSettings) {
		this.tokenSettings = tokenSettings;
	}

	public Instant getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Instant createdTs) {
		this.createdTs = createdTs;
	}

	public Instant getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Instant updatedTs) {
		this.updatedTs = updatedTs;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "RegisteredClient [id=" + id + ", clientId=" + clientId + ", clientName=" + clientName
				+ ", clientSecret=" + clientSecret + ", clientIdIssuedAt=" + clientIdIssuedAt
				+ ", clientSecretExpiresAt=" + clientSecretExpiresAt + ", clientAuthenticationMethods="
				+ clientAuthenticationMethods + ", authorizationGrantTypes=" + authorizationGrantTypes
				+ ", redirect_uris=" + redirect_uris + ", scopes=" + scopes + ", clientSettings=" + clientSettings
				+ ", tokenSettings=" + tokenSettings + ", createdTs=" + createdTs + ", updatedTs=" + updatedTs
				+ ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + "]";
	}

}
