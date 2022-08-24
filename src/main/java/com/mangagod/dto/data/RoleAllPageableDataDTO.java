package com.mangagod.dto.data;

import java.util.List;
import com.mangagod.dto.data.pagination.PageableDataDTOImpl;

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