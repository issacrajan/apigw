/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.issac.apigw.TestDTO;
import com.issac.apigw.constant.Constants;
import com.issac.apigw.dto.ClientIdpInfoDTO;
import com.issac.apigw.dto.JwtDTO;
import com.issac.apigw.dto.UserLoginStateDTO;
import com.issac.apigw.service.ClientIdpInfoService;
import com.issac.apigw.service.EndpointFactory;
import com.issac.apigw.service.UserLoginStateService;

import jakarta.servlet.http.Cookie;
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

	private EndpointFactory endpointFactory;
	private ClientIdpInfoService clientIdpInfoService;
	private UserLoginStateService loginStateService;
	private ObjectMapper mapper;

	public AuthController(ClientIdpInfoService clientIdpInfoService, UserLoginStateService loginStateService,
			EndpointFactory endpointFactory) {
		this.clientIdpInfoService = clientIdpInfoService;
		this.loginStateService = loginStateService;
		this.endpointFactory = endpointFactory;
		mapper = new ObjectMapper();
	}

	@GetMapping(path = "/api/test", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TestDTO> test(HttpServletRequest req) {
		TestDTO t = new TestDTO();
		Enumeration<String> headerNames = req.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			System.out.println(name + " : " + req.getHeader(name));
		}
		System.out.println("ContentType " + req.getContentType());
		System.out.println("ContextPath " + req.getContextPath());
		System.out.println("Local Addr " + req.getLocalAddr());
		System.out.println("Local Name " + req.getLocalName());
		System.out.println("ContentType " + req.getLocalPort());
		System.out.println("Path Info " + req.getPathInfo());
		System.out.println("Path Translated " + req.getPathTranslated());
		System.out.println("Protocol " + req.getProtocol());
		System.out.println("Protocol ReqID: " + req.getProtocolRequestId());
		System.out.println("Remote Addr: " + req.getRemoteAddr());
		System.out.println("Remote Port " + req.getRemotePort());
		System.out.println("Remote User " + req.getRemoteUser());
		System.out.println("Request Session ID " + req.getRequestedSessionId());
		System.out.println("Request ID " + req.getRequestId());
		System.out.println("Request UIR " + req.getRequestURI());
		System.out.println("Scheme " + req.getScheme());
		System.out.println("ServerName " + req.getServerName());
		System.out.println("ServerPort " + req.getServerPort());
		System.out.println("ServletPath " + req.getServletPath());

		return ResponseEntity.ok(t);
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
				String state = UUID.randomUUID().toString();
				String idpProvider = idpInfo.getIdpProvider();
				String authEndpoint = endpointFactory.getEndpoint(idpProvider).getAuthorizationEndpoint();
				StringBuilder url = new StringBuilder(authEndpoint);
				url.append("?").append("response_type=code");
				url.append("&scope=openid");
				url.append("&client_id=").append(idpInfo.getClientId());
				url.append("&redirect_uri=").append(URLEncoder.encode(idpInfo.getRedirectUri(), "UTF-8"));
				url.append("&state=").append(state);

				loginStateService.save(idpInfo.getClientId(), state);
				response.sendRedirect(url.toString());
			} else {
				// not implemented
			}
		}

	}

	// http://127.0.0.1:9000/auth/callback?code=oNQnHKXs8rZwpKDLrX72G0B5JsQWBnDPl-HYQkkCn07rEZiibTtkytq4lQiUo8hvHgxiS5OBxfgsAvpZd0eIfQc5C336QECQLbfiyYN_DHM0Lv8sn9Bt0OIdbsYJAyGK
	@GetMapping("/auth/callback")
	public void oauth2Callback(HttpServletResponse response, @RequestParam(name = "code") String code,
			@RequestParam(name = "state", required = false) String state) throws Exception {

		UserLoginStateDTO stateDTO = loginStateService.findByState(state);
		loginStateService.deleteUsedState(state);
		ClientIdpInfoDTO idpInfo = clientIdpInfoService.findClientInfoUsingClientId(stateDTO.getClientId());
		String idpProvider = idpInfo.getIdpProvider();
		String tokenEndpoint = endpointFactory.getEndpoint(idpProvider).getTokenEndpoint();
		System.out.println(code);
		String clientSecret = idpInfo.getClientSecret();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setBasicAuth(stateDTO.getClientId(), clientSecret);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", "authorization_code");
		map.add("code", code);
		map.add("redirect_uri", idpInfo.getRedirectUri());

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		ResponseEntity<String> exchange = restTemplate.exchange(tokenEndpoint, HttpMethod.POST, entity,
				String.class);
		System.out.println(exchange.getBody());
		if (exchange.getStatusCode().is2xxSuccessful()) {

			JwtDTO jwtDTO = mapper.readValue(exchange.getBody(), JwtDTO.class);
			addCookie(response, jwtDTO.getIdToken());
			response.sendRedirect("/pages/home");
		} else {
			// TODO handle error
		}

	}

	private void addCookie(HttpServletResponse response, String idToken) {
		Cookie c1 = new Cookie("jv-portal-id", idToken);
		c1.setHttpOnly(true);
		c1.setSecure(false);
		c1.setMaxAge(60 * 24);
		c1.setAttribute("SameSite", "strict");
		response.addCookie(c1);
	}
}
