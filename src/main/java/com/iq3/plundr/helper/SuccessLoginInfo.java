package com.iq3.plundr.helper;

import java.io.Serializable;

public class SuccessLoginInfo implements Serializable {

	private String jwtToken;

	public SuccessLoginInfo() {}

	public SuccessLoginInfo(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	// Getters and setters
	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
