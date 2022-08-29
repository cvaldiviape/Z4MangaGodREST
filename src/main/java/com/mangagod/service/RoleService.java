package com.mangagod.service;

import com.mangagod.dto.data.RoleAllPageableDataDTO;
import com.mangagod.dto.data.RoleDataDTO;
import com.mangagod.dto.request.RoleCreateRequestDTO;
import com.mangagod.dto.request.RoleUpdateRequestDTO;
import com.mangagod.service.generic.CrudService;

public interface RoleService extends CrudService<RoleAllPageableDataDTO, RoleDataDTO, RoleCreateRequestDTO, RoleUpdateRequestDTO>{
	
}