/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * @author issac
 *
 */
public class LoginDTO {

	@NotBlank(message = "email is required")
	private String email;

	@NotBlank(message = "password is required")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDTO [email=" + email + ", password=" + password + "]";
	}

}
