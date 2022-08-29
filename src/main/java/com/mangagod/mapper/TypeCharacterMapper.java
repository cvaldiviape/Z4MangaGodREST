package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mangagod.dto.data.TypeCharacterDataDTO;
import com.mangagod.dto.request.TypeCharacterCreateRequestDTO;
import com.mangagod.entity.TypeCharacterEntity;

@Component
public class TypeCharacterMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public TypeCharacterDataDTO mapTypeCharacterEntityToTypeCharacterDataDTO(TypeCharacterEntity typeCharacterEntity) {
		TypeCharacterDataDTO typeCharacterDataDTO = this.modelMapper.map(typeCharacterEntity, TypeCharacterDataDTO.class);
		return typeCharacterDataDTO;
	}
	
	public TypeCharacterEntity mapTypeCharacterCreateRequestToTypeCharacterEntity(TypeCharacterCreateRequestDTO typeCharacterCreateRequestDTO) {
		typeCharacterCreateRequestDTO.setName(typeCharacterCreateRequestDTO.getName().trim());
		TypeCharacterEntity typeCharacterEntity = this.modelMapper.map(typeCharacterCreateRequestDTO, TypeCharacterEntity.class);
		return typeCharacterEntity;
	}
	
}