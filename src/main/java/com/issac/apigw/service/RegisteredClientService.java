/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.service;

import org.springframework.stereotype.Service;

import com.issac.apigw.dto.RegisteredClientDTO;
import com.issac.apigw.entity.RegisteredClientEntity;
import com.issac.apigw.repo.RegisteredClientRepo;

/**
 * @author issac
 *
 */
@Service
public class RegisteredClientService {

	private RegisteredClientRepo clientRepo;

	public RegisteredClientService(RegisteredClientRepo clientRepo) {
		this.clientRepo = clientRepo;
	}

	public RegisteredClientDTO findByClientId(String clientId) {
		RegisteredClientEntity clientEntity = clientRepo.findByClientId(clientId);
		return RegisteredClientDTO.fromEntity(clientEntity);
	}
}
