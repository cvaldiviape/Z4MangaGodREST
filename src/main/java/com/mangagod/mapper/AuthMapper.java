package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mangagod.dto.data.UserAuthDataDTO;
import com.mangagod.entity.UserEntity;

@Component
public class AuthMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public UserAuthDataDTO mapUserEntityToUserAuthDataDTO(UserEntity userEntity) {
		UserAuthDataDTO userAuthDataDTO = this.modelMapper.map(userEntity, UserAuthDataDTO.class);
		return userAuthDataDTO;
	}

}