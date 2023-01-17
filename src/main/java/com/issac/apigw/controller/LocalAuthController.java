/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.issac.apigw.dto.LoginDTO;
import com.issac.apigw.service.ClientIdpInfoService;
import com.issac.apigw.service.EndpointFactory;
import com.issac.apigw.service.UserLoginStateService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * @author issac
 *
 */
@RestController
public class LocalAuthController {
	private static final Logger logger = LoggerFactory.getLogger(LocalAuthController.class);

	@Value("${idp.endpoint}")
	private String idpEndpoint;

	public LocalAuthController(ClientIdpInfoService clientIdpInfoService,
			UserLoginStateService loginStateService, EndpointFactory endpointFactory) {

	}

	@PostMapping(path = "/auth/authlocal/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(HttpServletRequest req,
			HttpServletResponse res,
			@Validated @RequestBody LoginDTO logindTO) {

		HttpSession session = req.getSession();
		System.out.println(session);

		RestTemplate rt = new RestTemplate();
		String endpoint = idpEndpoint.concat("/auth/authlocal/login");
		HttpEntity<LoginDTO> h = new HttpEntity<>(logindTO);
		ResponseEntity<String> resp = rt.exchange(endpoint, HttpMethod.POST, h, String.class);
		String idToken = resp.getBody();
		addCookie(res, idToken);
		logger.info(resp.toString());
		System.out.println(resp);
		return resp;
	}

	private void addCookie(HttpServletResponse response, String idToken) {
		Cookie c1 = new Cookie("issac-portal-id", idToken);
		c1.setHttpOnly(true);
		c1.setSecure(false); // TODO
		c1.setMaxAge(60 * 24);
		c1.setAttribute("SameSite", "strict");
		response.addCookie(c1);
	}
}
