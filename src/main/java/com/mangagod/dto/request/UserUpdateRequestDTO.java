package com.mangagod.dto.request;

import java.util.Set;

public class UserUpdateRequestDTO {

	private String username;
	private String email;
	private Set<Integer> roleIds;
	
	public UserUpdateRequestDTO() {
		
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

	public Set<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	
}
