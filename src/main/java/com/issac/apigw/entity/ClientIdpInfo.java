/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * @author issac
 *
 */
@Entity
@Table(name = "client_idp_info")
public class ClientIdpInfo {

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
		return "ClientIdpInfo [clientId=" + clientId + ", idpUrl=" + idpUrl + ", domainName=" + domainName
				+ ", loginMethod=" + loginMethod + "]";
	}

}
