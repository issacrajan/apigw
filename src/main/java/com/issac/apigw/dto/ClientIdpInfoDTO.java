/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.dto;

import java.util.Objects;

import com.issac.apigw.entity.ClientIdpInfo;

/**
 * @author issac
 *
 */
public class ClientIdpInfoDTO {

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

	public static ClientIdpInfoDTO convertEntity(ClientIdpInfo entity) {
		ClientIdpInfoDTO dto = new ClientIdpInfoDTO();
		dto.setClientId(entity.getClientId());
		dto.setDomainName(entity.getDomainName());
		dto.setIdpBaseUrl(entity.getIdpBaseUrl());
		dto.setLoginMethod(entity.getLoginMethod());
		dto.setRedirectUri(entity.getRedirectUri());
		dto.setIdpProvider(entity.getIdpProvider());

		return dto;
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
		ClientIdpInfoDTO other = (ClientIdpInfoDTO) obj;
		return Objects.equals(clientId, other.clientId);
	}

	@Override
	public String toString() {
		return "ClientIdpInfoDTO [clientId=" + clientId + ", idpBaseUrl=" + idpBaseUrl + ", domainName="
				+ domainName + ", loginMethod=" + loginMethod + ", redirectUri=" + redirectUri
				+ ", idpProvider=" + idpProvider + "]";
	}

}
