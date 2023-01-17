/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.issac.apigw.constant.Constants;
import com.issac.apigw.dto.ClientIdpInfoDTO;
import com.issac.apigw.dto.ErrorMsgDTO;
import com.issac.apigw.service.ClientIdpInfoService;
import com.issac.apigw.util.Util;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author issac
 *
 */
public class ApiValidatorFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(ApiValidatorFilter.class);
	private static final List<String> anonimousURIs = new ArrayList<>();

	private ObjectMapper mapper = new ObjectMapper();

	static {
		anonimousURIs.add("/api/about");
		anonimousURIs.add("/auth/login");
		anonimousURIs.add("/auth/callback");
	}

	private String clientIdFinder;
	private ClientIdpInfoService clientIdpInfoService;

	/**
	 * @param clientIdFinder
	 * @param clientIdpInfoService
	 */
	public ApiValidatorFilter(String clientIdFinder, ClientIdpInfoService clientIdpInfoService) {
		this.clientIdFinder = clientIdFinder;
		this.clientIdpInfoService = clientIdpInfoService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		req.getSession(false);
		HttpServletResponse res = (HttpServletResponse) response;

		logger.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
		String xClientID = req.getHeader("X-Client-ID");
//		if (isAuthenticationRequired(req.getRequestURI())) {
//			String xClientID = req.getHeader("X-Client-ID");
//			if (Util.isBlank(xClientID)) {
//				res.sendError(HttpStatus.BAD_REQUEST.value(), "Client Info missing");
//				return;
//			}
//			
//			//get authentication method - using
//		}
		ClientIdpInfoDTO clientIdpInfo = null;
		if (Constants.IDENTITY_CLEINT_USING_DOMAIN_NAME.equals(clientIdFinder)) {
			String serverName = request.getServerName();
			clientIdpInfo = clientIdpInfoService.getClientIdpInfo(serverName);
		} else if (Constants.IDENTITY_CLEINT_USING_CLIENT_ID.contentEquals(clientIdFinder)) {
			clientIdpInfo = clientIdpInfoService.findClientInfoUsingClientId(xClientID);
		}

		if (clientIdpInfo == null) {
			// invalid client identification method
			// return error response
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			PrintWriter out = res.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			ErrorMsgDTO errMsg = new ErrorMsgDTO(HttpServletResponse.SC_BAD_REQUEST,
					"Empty/Invalid value for Client-ID-Finder: " + clientIdFinder);
			out.print(mapper.writeValueAsString(errMsg));
			out.flush();
			return;
		}

		if (isAuthenticationRequired(req.getRequestURI())) {
			String sessionId = getSessionId(req);
			if (Util.isBlank(sessionId)) {
				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				// doFilter not required here. stopping the filter chain
				return;
			} else {

			}
		}

		Cookie c1 = new Cookie("token-id", UUID.randomUUID().toString());
		c1.setHttpOnly(true);
//		c1.setSecure(true);
		res.addCookie(c1);
		chain.doFilter(request, response);
		logger.info("Logging Response :{}", res.getContentType());
	}

	private boolean isAuthenticationRequired(String uri) {
		return !anonimousURIs.contains(uri);
	}

	private String getSessionId(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		for (Cookie cookie : cookies) {
			if ("PortalSESSIONID".equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}
}
