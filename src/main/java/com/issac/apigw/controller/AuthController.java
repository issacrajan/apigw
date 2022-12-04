/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.controller;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.issac.apigw.constant.Constants;
import com.issac.apigw.dto.ClientIdpInfoDTO;
import com.issac.apigw.service.ClientIdpInfoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author issac
 *
 */
@RestController
public class AuthController {

	// identity the client using - DomainName, TenentID
	@Value("${idp.client.finder:DomainName}")
	private String clientIdFinder;

	private ClientIdpInfoService clientIdpInfoService;

	public AuthController(ClientIdpInfoService clientIdpInfoService) {
		this.clientIdpInfoService = clientIdpInfoService;
	}

	@GetMapping("/auth/login")
	public void login(@RequestParam(name = "client_id", required = false) String clientId,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		ClientIdpInfoDTO idpInfo = null;

		if (Constants.IDENTITY_CLEINT_USING_DOMAIN_NAME.equals(clientIdFinder)) {
			String serverName = request.getServerName();
			idpInfo = clientIdpInfoService.getClientIdpInfo(serverName);
		} else if (Constants.IDENTITY_CLEINT_USING_CLIENT_ID.contentEquals(clientIdFinder)) {
			idpInfo = clientIdpInfoService.findClientInfoUsingClientId(clientId);
		} else {
			// invalid client identification method
			// return error response
			return;
		}
		if (idpInfo == null) {
			// return error
		} else {
			String loginMethod = idpInfo.getLoginMethod();
			if (Constants.LOGIN_METHOD_OPENID.endsWith(loginMethod)) {
				StringBuilder url = new StringBuilder(idpInfo.getIdpUrl());
				url.append("?").append("response_type=code");
				url.append("&client_id=").append(idpInfo.getClientId());
				url.append("&redirect_uri=").append(URLEncoder.encode(idpInfo.getRedirectUri(), "UTF-8"));
				response.sendRedirect(url.toString());
			} else {
				// not implemented
			}
		}

	}

	// http://127.0.0.1:9000/auth/callback?code=oNQnHKXs8rZwpKDLrX72G0B5JsQWBnDPl-HYQkkCn07rEZiibTtkytq4lQiUo8hvHgxiS5OBxfgsAvpZd0eIfQc5C336QECQLbfiyYN_DHM0Lv8sn9Bt0OIdbsYJAyGK
	@GetMapping("/auth/callback")
	public void oauth2Callback(@RequestParam(name = "code") String code,
			@RequestParam(name = "state", required = false) String state) {

	}
}
