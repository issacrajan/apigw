/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.dto;

import java.util.Objects;

import com.issac.apigw.entity.UserLoginState;

/**
 * @author issac
 *
 */
public class UserLoginStateDTO {

	private String stateId;
	private String clientId;

	public static UserLoginStateDTO fromEntity(UserLoginState loginState) {
		UserLoginStateDTO dto = new UserLoginStateDTO();
		dto.setClientId(loginState.getClientId());
		dto.setStateId(loginState.getStateId());

		return dto;
	}

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
		UserLoginStateDTO other = (UserLoginStateDTO) obj;
		return Objects.equals(stateId, other.stateId);
	}

	@Override
	public String toString() {
		return "UserLoginState [stateId=" + stateId + ", clientId=" + clientId + "]";
	}

}
