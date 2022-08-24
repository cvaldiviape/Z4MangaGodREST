package com.mangagod.service;

import com.mangagod.dto.data.RoleAllPageableDataDTO;
import com.mangagod.dto.data.RoleDataDTO;
import com.mangagod.dto.request.RoleCreateRequestDTO;
import com.mangagod.dto.request.RoleUpdateRequestDTO;

public interface RoleService extends ServiceCrud<RoleAllPageableDataDTO, RoleDataDTO, RoleCreateRequestDTO, RoleUpdateRequestDTO>{
	
}