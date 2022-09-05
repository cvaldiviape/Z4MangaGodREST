package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mangagod.dto.data.MangakaDataDTO;
import com.mangagod.dto.request.MangakaRequestDTO;
import com.mangagod.entity.MangakaEntity;

@Component
public class MangakaMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public MangakaDataDTO mapEntityToDataDTO(MangakaEntity entity) {
		MangakaDataDTO dataDTO = this.modelMapper.map(entity, MangakaDataDTO.class);
		return dataDTO;
	}
	
	public MangakaEntity mapRequestToEntity(MangakaRequestDTO requestDTO) {
		MangakaEntity entity = this.modelMapper.map(requestDTO, MangakaEntity.class);
		return entity;
	}
		
}