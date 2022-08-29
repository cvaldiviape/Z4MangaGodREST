package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mangagod.dto.data.ProfessionDataDTO;
import com.mangagod.dto.request.ProfessionCreateRequestDTO;
import com.mangagod.entity.ProfessionEntity;

@Component
public class ProfessionMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public ProfessionDataDTO mapProfessionEntityToProfessionDataDTO(ProfessionEntity professionEntity) {
		ProfessionDataDTO professionDataDTO = this.modelMapper.map(professionEntity, ProfessionDataDTO.class);
		return professionDataDTO;
	}
	
	public ProfessionEntity mapProfessionCreateRequestToProfessionEntity(ProfessionCreateRequestDTO professionCreateRequestDTO) {
		professionCreateRequestDTO.setName(professionCreateRequestDTO.getName().trim());
		ProfessionEntity professionEntity = this.modelMapper.map(professionCreateRequestDTO, ProfessionEntity.class);
		return professionEntity;
	}
	
}