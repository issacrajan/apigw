/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.service;

import org.springframework.stereotype.Service;

import com.issac.apigw.dto.ClientIdpInfoDTO;
import com.issac.apigw.entity.ClientIdpInfo;
import com.issac.apigw.repo.ClientIdpInfoRepo;

/**
 * @author issac
 *
 */
@Service
public class ClientIdpInfoService {
	private ClientIdpInfoRepo clientIdpInfoRepo;

	public ClientIdpInfoService(ClientIdpInfoRepo clientIdpInfoRepo) {
		this.clientIdpInfoRepo = clientIdpInfoRepo;
	}

	public ClientIdpInfoDTO getClientIdpInfo(String domainName) {
		ClientIdpInfo clientIdpInfo = clientIdpInfoRepo.findByDomainName(domainName);
		return ClientIdpInfoDTO.convertEntity(clientIdpInfo);
	}
}
