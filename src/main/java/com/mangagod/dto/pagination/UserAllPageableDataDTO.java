package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.data.UserDataDTO;
import com.mangagod.dto.pagination.base.PageableDataDTOImpl;

public class UserAllPageableDataDTO extends PageableDataDTOImpl{

	private List<UserDataDTO> users;
	
	public UserAllPageableDataDTO() {
		
	}

	public List<UserDataDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDataDTO> users) {
		this.users = users;
	}
	
}