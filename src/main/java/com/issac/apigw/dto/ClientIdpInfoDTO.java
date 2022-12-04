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
	private String idpUrl;
	private String domainName;
	private String loginMethod;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getIdpUrl() {
		return idpUrl;
	}

	public void setIdpUrl(String idpUrl) {
		this.idpUrl = idpUrl;
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

	public static ClientIdpInfoDTO convertEntity(ClientIdpInfo entity) {
		ClientIdpInfoDTO dto = new ClientIdpInfoDTO();
		dto.setClientId(entity.getClientId());
		dto.setDomainName(entity.getDomainName());
		dto.setIdpUrl(entity.getIdpUrl());
		dto.setLoginMethod(entity.getLoginMethod());

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
		return "ClientIdpInfo [clientId=" + clientId + ", idpUrl=" + idpUrl + ", domainName=" + domainName
				+ ", loginMethod=" + loginMethod + "]";
	}

}
