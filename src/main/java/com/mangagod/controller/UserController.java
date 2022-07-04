package com.mangagod.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mangagod.dto.requestDto.UserCreateRequestDTO;
import com.mangagod.dto.requestDto.UserUpdateRequestDTO;
import com.mangagod.dto.responseDto.UserAllPageableResponseDTO;
import com.mangagod.dto.responseDto.UserResponseDTO;
import com.mangagod.service.UserService;
import com.mangagod.util.AppConstants;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user")
public class UserController {

	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private UserService userService;
	
	// ---------------------------------------------------------- controllers ----------------------------------------------------------- //
	@ApiOperation("Esta operacion se encarga de listar a todos los usuarios.")
	@PreAuthorize("hasRole('ADMIN')") 
	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(value = "numberPage", defaultValue = AppConstants.NUM_PAGE_DEFAULT, required = false) int numberPage,
		      											   @RequestParam(value = "sizePage", defaultValue = AppConstants.SIZE_PAGE_DEFAULT, required = false) int sizePage,
		      											   @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_DEFAULT, required = false) String sortBy,
		      											   @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_DEFAULT, required = false) String sortDir){
		ResponseEntity<Map<String, Object>> responseEntity = null;
		Map<String, Object> responseMap = null;
		UserAllPageableResponseDTO userAllPageableResponseDTO = this.userService.getAllUsers(numberPage, sizePage, sortBy, sortDir);
		if(userAllPageableResponseDTO != null) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("success", true);
			responseMap.put("data", userAllPageableResponseDTO);
			
			responseEntity = new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@ApiOperation("Esta operacion se encarga de obtener los datos de un usuario en base a su ID.")
	@PreAuthorize("hasRole('ADMIN')") 
	@GetMapping("/{user_id}")
	public ResponseEntity<Map<String, Object>> getUserById(@PathVariable (name = "user_id") int userId){
		ResponseEntity<Map<String, Object>> responseEntity = null;
		Map<String, Object> responseMap = null;
		UserResponseDTO userResponseDTO = this.userService.getUserById(userId);
		if(userResponseDTO != null) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("success", true);
			responseMap.put("data", userResponseDTO);
			
			responseEntity = new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@ApiOperation("Esta operacion se encarga de crear un nuevo usuario.")
	@PreAuthorize("hasRole('ADMIN')") 
	@PostMapping
	public ResponseEntity<Map<String, Object>> createUser(@RequestBody UserCreateRequestDTO userRequestDTO){
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
	
	@ApiOperation("Esta operacion se encarga de actualizar los datos de un usuario.")
	@PreAuthorize("hasRole('ADMIN')") 
	@PutMapping("/{user_id}")
	public ResponseEntity<Map<String, Object>> updateUser(@PathVariable (name = "user_id") int userId, 
			                                              @RequestBody UserUpdateRequestDTO userUpdateRequestDTO ){
		
		ResponseEntity<Map<String, Object>> responseEntity = null;
		Map<String, Object> responseMap = null;
		UserResponseDTO userResponseDTO = this.userService.updateUser(userId, userUpdateRequestDTO); 
		if(userResponseDTO != null) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("success", true);
			responseMap.put("message", "El usuario ha sido actualizado exitosamente!");
			responseMap.put("data", userResponseDTO);
			
			responseEntity = new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		}
		return responseEntity;
	}

	@ApiOperation("Esta operacion se encarga de eliminar a un usuarios en base a su ID.")
	@PreAuthorize("hasRole('ADMIN')") 
	@DeleteMapping("/{user_id}")
	public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable (name = "user_id") int userId){
		ResponseEntity<Map<String, Object>> responseEntity = null;
		Map<String, Object> responseMap = null;
		UserResponseDTO userResponseDTO = this.userService.deleteUser(userId);
		if(userResponseDTO != null) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("success", true);
			responseMap.put("message", "El usuario ha sido eliminado exitosamente!");
			responseMap.put("data", userResponseDTO);
			
			responseEntity = new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		}
		return responseEntity;
	}
	
}