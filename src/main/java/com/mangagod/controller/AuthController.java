package com.mangagod.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mangagod.dto.requestDto.AuthRequestDTO;
import com.mangagod.dto.requestDto.TokenRequestDTO;
import com.mangagod.service.AuthService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private AuthService authService;
	
	// ---------------------------------------------------------- controllers ----------------------------------------------------------- //
	@ApiOperation("Esta operacion se encarga de la autenticación del usuario.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody AuthRequestDTO authRequestDTO){
		
		ResponseEntity<Map<String, Object>> responseEntity = null;
		Map<String, Object> responseMap = null;
		Map<String, Object> dataAuth = null;
		
		dataAuth = this.authService.login(authRequestDTO);
		
		if(dataAuth != null) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("success", true);
			responseMap.put("message", "El usuario se ha autenticado exitosamente!");
			responseMap.put("data", dataAuth);
			
			responseEntity = new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
	@ApiOperation("Esta operacion se encarga de verificar que el token se encuentre vigente.")
	@PostMapping("/refresh-token")
	public ResponseEntity<Map<String, Object>> refreshToken(@RequestBody TokenRequestDTO tokenRequestDTO) throws ParseException{
		
		ResponseEntity<Map<String, Object>> responseEntity = null;
		Map<String, Object> responseMap = null;
		
		String tokenRefreshed = this.authService.refreshToken(tokenRequestDTO);
		if(tokenRefreshed != null) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("success", true);
			responseMap.put("message", "Token refrescado exitosamente!");
			responseMap.put("data", tokenRefreshed);
			
			responseEntity = new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
}