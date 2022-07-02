package com.mangagod.dto.requestDto;

public class AuthRequestDTO {

	private String usernameOrEmail;
	private String password;

	public AuthRequestDTO() {

	}

	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
