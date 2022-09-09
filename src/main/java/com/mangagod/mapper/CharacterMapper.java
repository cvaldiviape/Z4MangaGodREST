package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mangagod.dto.data.CharacterDataDTO;
import com.mangagod.dto.request.CharacterRequestDTO;
import com.mangagod.entity.CharacterEntity;

@Component
public class CharacterMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public CharacterDataDTO mapEntityToDataDTO(CharacterEntity entity) {
		CharacterDataDTO dataDTO = this.modelMapper.map(entity, CharacterDataDTO.class);
		return dataDTO;
	}
	
	public CharacterEntity mapRequestToEntity(CharacterRequestDTO requestDTO) {
		this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		CharacterEntity entity = this.modelMapper.map(requestDTO, CharacterEntity.class);
		return entity;
	}
	
}