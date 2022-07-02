package com.mangagod.service;

import com.mangagod.dto.requestDto.UserRequestDTO;
import com.mangagod.dto.responseDto.UserResponseDTO;

public interface UserService {
	
	public UserResponseDTO getUserById(Integer id);
	
	public UserResponseDTO createUser(UserRequestDTO userDTO);
	
	public UserResponseDTO updateUser(UserRequestDTO userDTO, Integer id);
	
	public UserResponseDTO deleteUser(Integer id);
	
}
