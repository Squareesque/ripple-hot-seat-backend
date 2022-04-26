package com.comarch.ripplehotseat.jwtutils.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 3642704351507671558L;

	private final String token;
	   
	public JwtResponse(String token) {
		this.token = token;
	}
	
	public String getToken() {
	    return token;
	}
	
}
