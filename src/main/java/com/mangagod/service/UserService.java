package com.mangagod.service;

import com.mangagod.dto.requestDto.UserCreateRequestDTO;
import com.mangagod.dto.requestDto.UserUpdateRequestDTO;
import com.mangagod.dto.responseDto.UserAllPageableResponseDTO;
import com.mangagod.dto.responseDto.UserResponseDTO;

public interface UserService {
	
	public UserAllPageableResponseDTO getAllUsers(Integer numberPage, Integer sizePage, String sortBy, String sortDir);
	
	public UserResponseDTO getUserById(Integer id);
	
	public UserResponseDTO createUser(UserCreateRequestDTO userCreateRequestDTO);
	
	public UserResponseDTO updateUser(Integer id, UserUpdateRequestDTO userUpdateRequestDTO);
	
	public UserResponseDTO deleteUser(Integer id);
	
}
