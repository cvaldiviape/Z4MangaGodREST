package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mangagod.dto.data.RoleDataDTO;
import com.mangagod.dto.request.RoleRequestDTO;
import com.mangagod.entity.RoleEntity;

@Component
public class RoleMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public RoleDataDTO mapEntityToDataDTO(RoleEntity entity) {
		RoleDataDTO dataDTO = this.modelMapper.map(entity, RoleDataDTO.class);
		return dataDTO;
	}
	
	public RoleEntity mapRequestToEntity(RoleRequestDTO requestDTO) {
		requestDTO.setName(requestDTO.getName().trim());
		requestDTO.setDescription(requestDTO.getDescription().trim());
		RoleEntity entity = this.modelMapper.map(requestDTO, RoleEntity.class);
		return entity;
	}
	
}