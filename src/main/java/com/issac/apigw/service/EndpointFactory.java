/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.issac.apigw.constant.Constants;
import com.issac.apigw.dto.EndpointDTO;
import com.issac.apigw.entity.ClientIdpInfo;
import com.issac.apigw.repo.ClientIdpInfoRepo;

import jakarta.annotation.PostConstruct;

/**
 * @author issac
 *
 */
@Component
public class EndpointFactory {
	private Map<String, EndpointDTO> endpointMap = new HashMap<>();

	private ClientIdpInfoRepo clientIdpInfoRepo;

	public EndpointFactory(ClientIdpInfoRepo clientIdpInfoRepo) {
		this.clientIdpInfoRepo = clientIdpInfoRepo;
	}

	public EndpointDTO getEndpoint(String provider) {
		return endpointMap.get(provider);
	}

	@PostConstruct
	private void init() {
		List<ClientIdpInfo> clientInfoList = clientIdpInfoRepo.findAll();
		String provider;
		String providerBaseUrl;

		for (ClientIdpInfo idp : clientInfoList) {
			provider = idp.getIdpProvider();
			providerBaseUrl = idp.getIdpBaseUrl();

			if (Constants.IDP_PROVIDER_SPRING.equals(provider)) {
				EndpointDTO dto = new EndpointDTO();
				dto.setAuthorizationEndpoint(providerBaseUrl + "/oauth2/authorize");
				dto.setTokenEndpoint(providerBaseUrl + "/oauth2/token");
				dto.setJwkSetEndpoint(providerBaseUrl + "/oauth2/jwks");

				endpointMap.put(provider, dto);
				System.out.println(dto);
			} else {
				throw new RuntimeException(
						"not yet implemented for provider " + provider + "; URL: " + providerBaseUrl);

			}
		}

	}
}
