package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mangagod.dto.data.DemographyDataDTO;
import com.mangagod.dto.request.DemographyCreateRequestDTO;
import com.mangagod.entity.DemographyEntity;

@Component
public class DemographyMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public DemographyDataDTO mapDemographyEntityToDemographyDataDTO(DemographyEntity demographyEntity) {
		DemographyDataDTO demographyDataDTO = this.modelMapper.map(demographyEntity, DemographyDataDTO.class);
		return demographyDataDTO;
	}
	
	public DemographyEntity mapDemographyCreateRequestToDemographyEntity(DemographyCreateRequestDTO demographyCreateRequestDTO) {
		demographyCreateRequestDTO.setName(demographyCreateRequestDTO.getName().trim());
		DemographyEntity demographyEntity = this.modelMapper.map(demographyCreateRequestDTO, DemographyEntity.class);
		return demographyEntity;
	}
	
}