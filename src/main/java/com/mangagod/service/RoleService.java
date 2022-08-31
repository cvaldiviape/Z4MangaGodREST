package com.mangagod.service;

import com.mangagod.dto.data.RoleDataDTO;
import com.mangagod.dto.pagination.RoleAllPageableDataDTO;
import com.mangagod.dto.request.RoleRequestDTO;
import com.mangagod.service.base.BaseService;

public interface RoleService extends BaseService<RoleAllPageableDataDTO, RoleDataDTO, RoleRequestDTO, Integer>{
	
}