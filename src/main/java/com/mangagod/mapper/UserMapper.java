package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mangagod.dto.data.UserDataDTO;
import com.mangagod.dto.request.UserCreateRequestDTO;
import com.mangagod.entity.UserEntity;

@Component
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public UserDataDTO mapEntityToDataDTO(UserEntity userEntity) {
		UserDataDTO userResponseDTO = this.modelMapper.map(userEntity, UserDataDTO.class);
		return userResponseDTO;
	}
	
	public UserEntity mapRequestToEntity(UserCreateRequestDTO requestDTO) {
		UserEntity userEntity = this.modelMapper.map(requestDTO, UserEntity.class);
		return userEntity;
	}
		
}