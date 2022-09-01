package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mangagod.dto.data.DemographyDataDTO;
import com.mangagod.dto.request.DemographyRequestDTO;
import com.mangagod.entity.DemographyEntity;

@Component
public class DemographyMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public DemographyDataDTO mapEntityToDataDTO(DemographyEntity entity) {
		DemographyDataDTO dataDTO = this.modelMapper.map(entity, DemographyDataDTO.class);
		return dataDTO;
	}
	
	public DemographyEntity mapRequestToEntity(DemographyRequestDTO requestDTO) {
		DemographyEntity entity = this.modelMapper.map(requestDTO, DemographyEntity.class);
		return entity;
	}
	
}