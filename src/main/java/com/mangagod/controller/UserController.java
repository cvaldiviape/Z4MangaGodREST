package com.mangagod.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mangagod.dto.requestDto.UserRequestDTO;
import com.mangagod.dto.responseDto.UserResponseDTO;
import com.mangagod.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user")
public class UserController {

	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private UserService userService;
	
	// ---------------------------------------------------------- controllers ----------------------------------------------------------- //
	@ApiOperation("Esta operacion se encarga de crear un nuevo usuario.")
	@PostMapping
	public ResponseEntity<Map<String, Object>> createUser(@RequestBody UserRequestDTO userRequestDTO){
		ResponseEntity<Map<String, Object>> responseEntity = null;
		Map<String, Object> responseMap = null;
		UserResponseDTO userResponseDTO = this.userService.createUser(userRequestDTO); 
		if(userResponseDTO != null) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("success", true);
			responseMap.put("message", "El usuario ha sido creado exitosamente!");
			responseMap.put("data", userResponseDTO);
			
			responseEntity = new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		}
		return responseEntity;		
	}
	
	@ApiOperation("Esta operacion se encarga de obtener los datos de un usuario en base a su ID.")
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getUserById(@PathVariable (name = "id") int id){
		ResponseEntity<Map<String, Object>> responseEntity = null;
		Map<String, Object> responseMap = null;
		UserResponseDTO userResponseDTO = this.userService.getUserById(id);
		if(userResponseDTO != null) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("success", true);
			responseMap.put("data", userResponseDTO);
			
			responseEntity = new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		}
		return responseEntity;
	}
	
}