package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.data.RoleDataDTO;
import com.mangagod.dto.pagination.base.PageableDataDTOImpl;

public class RoleAllPageableDataDTO extends PageableDataDTOImpl{

	private List<RoleDataDTO> roles;
	
	public RoleAllPageableDataDTO() {
		
	}

	public List<RoleDataDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDataDTO> roles) {
		this.roles = roles;
	}
	
}