/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.service;

import org.springframework.stereotype.Service;

import com.issac.apigw.dto.UserLoginStateDTO;
import com.issac.apigw.entity.UserLoginState;
import com.issac.apigw.exception.RecordNotFoundEx;
import com.issac.apigw.repo.UserLoginStateRepo;

/**
 * @author issac
 *
 */

@Service
public class UserLoginStateService {

	private UserLoginStateRepo loginStateRepo;

	public UserLoginStateService(UserLoginStateRepo loginStateRepo) {
		this.loginStateRepo = loginStateRepo;
	}

	public void save(String clientId, String state) {
		UserLoginState loginState = new UserLoginState();
		loginState.setStateId(state);
		loginState.setClientId(clientId);
		loginStateRepo.save(loginState);
	}

	public UserLoginStateDTO findByState(String stateId) {
		UserLoginState state = loginStateRepo.getReferenceById(stateId);
		if (state == null) {
			throw new RecordNotFoundEx("record not found state: " + stateId);
		}
		return UserLoginStateDTO.fromEntity(state);
	}

	public void deleteUsedState(String stateId) {
		loginStateRepo.deleteById(stateId);
	}
}
