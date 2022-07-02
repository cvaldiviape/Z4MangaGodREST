package com.mangagod.dto.responseDto;

import java.util.Set;

public class AuthResponseDTO {
	
	private Integer id;
	private String username;
	private String email;
	private String tokenAcces;
	private String tokenType;
	private Set<RoleResponseDTO> roles;

	public AuthResponseDTO() {
		this.tokenType = "Bearer";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTokenAcces() {
		return tokenAcces;
	}

	public void setTokenAcces(String tokenAcces) {
		this.tokenAcces = tokenAcces;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Set<RoleResponseDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleResponseDTO> roles) {
		this.roles = roles;
	}

}
