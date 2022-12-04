/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
		Assert.hasText(domainName, "domainName cannot be empty");
		ClientIdpInfo clientIdpInfo = clientIdpInfoRepo.findByDomainName(domainName);
		return ClientIdpInfoDTO.convertEntity(clientIdpInfo);
	}

	public ClientIdpInfoDTO findClientInfoUsingClientId(String clientId) {
		Assert.hasText(clientId, "client id cannot be empty");
		ClientIdpInfo clientIdpInfo = clientIdpInfoRepo.getReferenceById(clientId);
		return ClientIdpInfoDTO.convertEntity(clientIdpInfo);
	}
}
