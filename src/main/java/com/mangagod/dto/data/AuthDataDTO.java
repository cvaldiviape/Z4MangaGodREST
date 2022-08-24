package com.mangagod.dto.data;

public class AuthDataDTO {

	public UserAuthDataDTO user;
	public RoleDataDTO role;
	public TokenDataDTO token;
	
	public AuthDataDTO() {
		
	}

	public UserAuthDataDTO getUser() {
		return user;
	}

	public void setUser(UserAuthDataDTO user) {
		this.user = user;
	}

	public RoleDataDTO getRole() {
		return role;
	}

	public void setRole(RoleDataDTO role) {
		this.role = role;
	}

	public TokenDataDTO getToken() {
		return token;
	}

	public void setToken(TokenDataDTO token) {
		this.token = token;
	}
		
}