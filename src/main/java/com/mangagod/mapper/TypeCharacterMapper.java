package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mangagod.dto.data.TypeCharacterDataDTO;
import com.mangagod.dto.request.TypeCharacterRequestDTO;
import com.mangagod.entity.TypeCharacterEntity;

@Component
public class TypeCharacterMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public TypeCharacterDataDTO mapEntityToDataDTO(TypeCharacterEntity entity) {
		TypeCharacterDataDTO dataDTO = this.modelMapper.map(entity, TypeCharacterDataDTO.class);
		return dataDTO;
	}
	
	public TypeCharacterEntity mapRequestToEntity(TypeCharacterRequestDTO requestDTO) {
		TypeCharacterEntity entity = this.modelMapper.map(requestDTO, TypeCharacterEntity.class);
		return entity;
	}
	
}