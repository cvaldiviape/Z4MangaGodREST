package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mangagod.dto.data.ProfessionDataDTO;
import com.mangagod.dto.request.ProfessionRequestDTO;
import com.mangagod.entity.ProfessionEntity;

@Component
public class ProfessionMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public ProfessionDataDTO mapEntityToDataDTO(ProfessionEntity entity) {
		ProfessionDataDTO dataDTO = this.modelMapper.map(entity, ProfessionDataDTO.class);
		return dataDTO;
	}
	
	public ProfessionEntity mapRequestToEntity(ProfessionRequestDTO requestDTO) {
		requestDTO.setName(requestDTO.getName().trim());
		ProfessionEntity entity = this.modelMapper.map(requestDTO, ProfessionEntity.class);
		return entity;
	}
	
}