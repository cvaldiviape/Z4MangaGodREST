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
	public UserDataDTO mapUserEntityToUserDataDTO(UserEntity userEntity) {
		UserDataDTO userResponseDTO = this.modelMapper.map(userEntity, UserDataDTO.class);
		return userResponseDTO;
	}
	
	public UserEntity mapUserCreateRequestToUserEntity(UserCreateRequestDTO userRequestDTO) {
		UserEntity userEntity = this.modelMapper.map(userRequestDTO, UserEntity.class);
		return userEntity;
	}
		
}