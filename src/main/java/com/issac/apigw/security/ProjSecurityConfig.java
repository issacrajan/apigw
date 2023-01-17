/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.issac.apigw.service.ClientIdpInfoService;

/**
 * @author issac
 *
 */
@Configuration
public class ProjSecurityConfig {
	@Value("${idp.client.finder:DomainName}")
	private String clientIdFinder;

	@Autowired
	private ClientIdpInfoService clientIdpInfoService;

	@Bean
	public FilterRegistrationBean<ApiValidatorFilter> apiValidatorFilter() {
		FilterRegistrationBean<ApiValidatorFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new ApiValidatorFilter(clientIdFinder, clientIdpInfoService));
		registrationBean.addUrlPatterns("/api/*");
		registrationBean.setOrder(2);

		return registrationBean;
	}
}
