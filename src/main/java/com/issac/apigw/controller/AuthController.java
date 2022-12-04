/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author issac
 *
 */
@RestController
public class AuthController {

	@GetMapping("/auth/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String serverName = request.getServerName();
		System.out.println(serverName);
		response.sendRedirect(null);
	}

	// http://127.0.0.1:9000/auth/callback?code=oNQnHKXs8rZwpKDLrX72G0B5JsQWBnDPl-HYQkkCn07rEZiibTtkytq4lQiUo8hvHgxiS5OBxfgsAvpZd0eIfQc5C336QECQLbfiyYN_DHM0Lv8sn9Bt0OIdbsYJAyGK
	@GetMapping("/auth/callback")
	public void oauth2Callback(@RequestParam(name = "code") String code,
			@RequestParam(name = "state", required = false) String state) {

	}
}
