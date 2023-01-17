/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.cache;

import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @author issac
 *
 */
@Service
public class TokenCacheService {

	private CacheManager cacheManager;

	public TokenCacheService(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public String getTokenFromSessionId(String sessionId) {
		System.out.println("Calling getTokenFromSessionId method...");
		ValueWrapper valueWrapper = cacheManager.getCache("default").get(sessionId);
		return (String) valueWrapper.get();
	}

	public void setTokenFromSessionId(String sessionId, String token) {
		System.out.println("Calling setTokenFromSessionId method...");
		cacheManager.getCache("default").put(sessionId, token);
	}
}
