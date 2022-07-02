package com.mangagod.dto.requestDto;

import java.util.Set;

public class UserRequestDTO {

	private String username;
	private String email;
	private String password;
	private Set<Integer> roleIds;
	
	public UserRequestDTO() {
		
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	
}