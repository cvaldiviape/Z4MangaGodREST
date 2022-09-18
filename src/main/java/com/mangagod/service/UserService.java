package com.mangagod.service;

import com.mangagod.dto.pagination.UserAllPageableDataDTO;
import com.mangagod.dto.request.UserCreateRequestDTO;
import com.mangagod.dto.request.UserUpdateRequestDTO;
import com.mangagod.dto.response.UserResponseDTO;
import com.mangagod.service.base.BaseUserService;

public interface UserService extends BaseUserService<UserAllPageableDataDTO, UserResponseDTO, UserCreateRequestDTO, UserUpdateRequestDTO, Integer>{
	
	
}