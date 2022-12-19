/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.interceptor;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

/**
 * @author issac
 *
 */
@RestControllerAdvice
public class RequestAdvisor extends RequestBodyAdviceAdapter {

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		System.out.println("beforeBodyRead");
		System.out.println("inputMessage " + inputMessage);
		System.out.println("parameter " + parameter);
		System.out.println("targetType " + targetType);
		System.out.println("converterType " + converterType);

		return super.beforeBodyRead(inputMessage, parameter, targetType, converterType);
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		System.out.println("afterBodyRead");
		System.out.println("inputMessage " + inputMessage);
		System.out.println("body " + body);

		System.out.println("parameter " + parameter);
		System.out.println("targetType " + targetType);
		System.out.println("converterType " + converterType);
		return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		System.out.println("handleEmptyBody");
		System.out.println("inputMessage " + inputMessage);
		System.out.println("parameter " + parameter);
		System.out.println("targetType " + targetType);
		System.out.println("converterType " + converterType);
		return super.handleEmptyBody(body, inputMessage, parameter, targetType, converterType);
	}

}
