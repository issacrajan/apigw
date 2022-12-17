/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw;

/**
 * @author issac
 *
 */
public class TestDTO {

	private String name = "issac";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TestDTO [name=" + name + "]";
	}

}
