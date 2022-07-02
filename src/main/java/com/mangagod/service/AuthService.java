package com.mangagod.service;

import com.mangagod.dto.requestDto.AuthRequestDTO;
import com.mangagod.dto.responseDto.AuthResponseDTO;

public interface AuthService {
	
	public AuthResponseDTO login(AuthRequestDTO loginDTO);

}
