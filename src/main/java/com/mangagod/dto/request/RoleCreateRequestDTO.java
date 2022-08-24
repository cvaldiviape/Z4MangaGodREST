package com.mangagod.dto.request;

public class RoleCreateRequestDTO {

	private String name;
	private String description;
	
	public RoleCreateRequestDTO() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}