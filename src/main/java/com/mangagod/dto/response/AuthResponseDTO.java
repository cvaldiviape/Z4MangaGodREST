package com.mangagod.dto.response;

public class AuthResponseDTO {

	public UserAuthResponseDTO user;
	public RoleResponseDTO role;
	public TokenResponseDTO token;
	
	public AuthResponseDTO() {
		
	}
	
	public AuthResponseDTO(UserAuthResponseDTO user, RoleResponseDTO role, TokenResponseDTO token) {
		super();
		this.user = user;
		this.role = role;
		this.token = token;
	}
	
	public UserAuthResponseDTO getUser() {
		return user;
	}

	public void setUser(UserAuthResponseDTO user) {
		this.user = user;
	}

	public RoleResponseDTO getRole() {
		return role;
	}

	public void setRole(RoleResponseDTO role) {
		this.role = role;
	}

	public TokenResponseDTO getToken() {
		return token;
	}

	public void setToken(TokenResponseDTO token) {
		this.token = token;
	}
		
}