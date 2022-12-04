/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author issac
 *
 */
public class PKCE {

	public static void main(String[] args) throws Exception {
		String verifier = generateCodeVerifier();
		String codeChallenge = generateCodeChallange(verifier);

		System.out.println(verifier);
		System.out.println(codeChallenge);
		/*
		 * code_challenge=xxx&code_challenge_method=S256
		 * 
		 * in the next post call, include code_verifier
		 */
		// S256
	}

	public static String generateCodeVerifier() throws UnsupportedEncodingException {
		SecureRandom secureRandom = new SecureRandom();
		byte[] codeVerifier = new byte[32];
		secureRandom.nextBytes(codeVerifier);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier);
	}

	public static String generateCodeChallange(String codeVerifier)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] bytes = codeVerifier.getBytes("US-ASCII");
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(bytes, 0, bytes.length);
		byte[] digest = messageDigest.digest();
		return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
	}
}
