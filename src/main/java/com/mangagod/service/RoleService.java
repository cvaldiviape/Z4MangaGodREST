package com.mangagod.service;

import com.mangagod.dto.pagination.RoleAllPageableDataDTO;
import com.mangagod.dto.request.RoleRequestDTO;
import com.mangagod.dto.response.RoleResponseDTO;
import com.mangagod.service.base.BaseService;

public interface RoleService extends BaseService<RoleAllPageableDataDTO, RoleResponseDTO, RoleRequestDTO, Integer>{
	
}