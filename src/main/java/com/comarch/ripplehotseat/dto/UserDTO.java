package com.comarch.ripplehotseat.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDTO {

	private String id;
	private String login;
	private String password;
	private boolean isAdmin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public void encryptPassword()
	{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(this.password);
		this.password = encodedPassword;
	}
	
}
