package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.pagination.base.PageableDataDTOImpl;
import com.mangagod.dto.response.RoleResponseDTO;

public class RoleAllPageableDataDTO extends PageableDataDTOImpl{

	private List<RoleResponseDTO> roles;
	
	public RoleAllPageableDataDTO() {
		
	}

	public List<RoleResponseDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleResponseDTO> roles) {
		this.roles = roles;
	}
	
}