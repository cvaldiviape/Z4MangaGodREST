package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mangagod.dto.data.RoleDataDTO;
import com.mangagod.entity.RoleEntity;

@Component
public class RoleMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public RoleDataDTO mapRoleEntityToRoleDataDTO(RoleEntity roleEntity) {
		RoleDataDTO roleDataDTO = this.modelMapper.map(roleEntity, RoleDataDTO.class);
		return roleDataDTO;
	}
	
}