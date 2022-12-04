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
@Table(name = "user_login_state")
public class UserLoginState {

	@Id
	private String stateId;
	private String clientId;

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(stateId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLoginState other = (UserLoginState) obj;
		return Objects.equals(stateId, other.stateId);
	}

	@Override
	public String toString() {
		return "UserLoginState [stateId=" + stateId + ", clientId=" + clientId + "]";
	}

}
