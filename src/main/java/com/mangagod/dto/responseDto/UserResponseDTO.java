package com.mangagod.dto.responseDto;

import java.util.Set;

public class UserResponseDTO {
	
	private Integer id;
	private String username;
	private String email;
	private Set<RoleResponseDTO> roles;
	
	public UserResponseDTO() {
		
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

	public Set<RoleResponseDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleResponseDTO> roles) {
		this.roles = roles;
	}
	
}