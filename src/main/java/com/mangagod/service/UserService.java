package com.mangagod.service;

import com.mangagod.dto.data.UserAllPageableDataDTO;
import com.mangagod.dto.data.UserDataDTO;
import com.mangagod.dto.request.UserCreateRequestDTO;
import com.mangagod.dto.request.UserUpdateRequestDTO;
import com.mangagod.service.generic.CrudService;

public interface UserService extends CrudService<UserAllPageableDataDTO, UserDataDTO, UserCreateRequestDTO, UserUpdateRequestDTO>{
	
}