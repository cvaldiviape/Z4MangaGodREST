package com.mangagod.service;

import java.text.ParseException;
import java.util.Map;
import com.mangagod.dto.requestDto.AuthRequestDTO;
import com.mangagod.dto.requestDto.TokenRequestDTO;

public interface AuthService {
	
	public Map<String, Object> login(AuthRequestDTO loginDTO);
	
	public String refreshToken(TokenRequestDTO tokenRequestDTO) throws ParseException;

}