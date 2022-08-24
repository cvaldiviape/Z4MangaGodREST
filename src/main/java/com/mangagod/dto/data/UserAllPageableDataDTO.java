package com.mangagod.dto.data;

import java.util.List;

import com.mangagod.dto.data.pagination.PageableDataDTOImpl;

public class UserAllPageableDataDTO extends PageableDataDTOImpl{

	private List<UserDataDTO> users;
	
	public UserAllPageableDataDTO() {
		
	}

	public List<UserDataDTO> getUsers() {
		return users;
	}

	public void setList(List<UserDataDTO> users) {
		this.users = users;
	}
	
}