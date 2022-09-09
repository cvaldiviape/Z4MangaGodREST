package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mangagod.dto.data.StoryDataDTO;
import com.mangagod.dto.request.StoryRequestDTO;
import com.mangagod.entity.StoryEntity;

@Component
public class StoryMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public StoryDataDTO mapEntityToDataDTO(StoryEntity entity) {
		StoryDataDTO dataDTO = this.modelMapper.map(entity, StoryDataDTO.class);
		return dataDTO;
	}
	
	public StoryEntity mapRequestToEntity(StoryRequestDTO requestDTO) {
		this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		StoryEntity entity = this.modelMapper.map(requestDTO, StoryEntity.class);
		return entity;
	}
	
}