package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.pagination.base.PageableDataDTOImpl;
import com.mangagod.dto.response.UserResponseDTO;

public class UserAllPageableDataDTO extends PageableDataDTOImpl{

	private List<UserResponseDTO> users;
	
	public UserAllPageableDataDTO() {
		
	}

	public List<UserResponseDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserResponseDTO> users) {
		this.users = users;
	}
	
}