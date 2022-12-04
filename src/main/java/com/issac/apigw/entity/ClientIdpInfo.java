/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author issac
 *
 */
@Entity
@Table(name = "client_idp_info")
public class ClientIdpInfo {

	@Id
	private String clientId;
	private String idpBaseUrl;
	private String domainName;
	private String loginMethod;
	private String redirectUri;
	private String idpProvider;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getIdpBaseUrl() {
		return idpBaseUrl;
	}

	public void setIdpBaseUrl(String idpBaseUrl) {
		this.idpBaseUrl = idpBaseUrl;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getLoginMethod() {
		return loginMethod;
	}

	public void setLoginMethod(String loginMethod) {
		this.loginMethod = loginMethod;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public String getIdpProvider() {
		return idpProvider;
	}

	public void setIdpProvider(String idpProvider) {
		this.idpProvider = idpProvider;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientIdpInfo other = (ClientIdpInfo) obj;
		return Objects.equals(clientId, other.clientId);
	}

	@Override
	public String toString() {
		return "ClientIdpInfo [clientId=" + clientId + ", idpBaseUrl=" + idpBaseUrl + ", domainName="
				+ domainName + ", loginMethod=" + loginMethod + ", redirectUri=" + redirectUri
				+ ", idpProvider=" + idpProvider + "]";
	}

}
