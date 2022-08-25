package com.mangagod.dto.request;

public class CountryUpdateRequestDTO {

	private Integer id;
	private String name;
	
	public CountryUpdateRequestDTO() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}