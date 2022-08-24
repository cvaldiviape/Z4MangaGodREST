package com.mangagod.service;

import com.mangagod.dto.data.AuthDataDTO;
import com.mangagod.dto.request.AuthRequestDTO;
import com.mangagod.dto.request.TokenRequestDTO;

public interface AuthService {
	
	public AuthDataDTO login(AuthRequestDTO authRequestDTO);
	
	public String refreshToken(TokenRequestDTO tokenRequestDTO);

}