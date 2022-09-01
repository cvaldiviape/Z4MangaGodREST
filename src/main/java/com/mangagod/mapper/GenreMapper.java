package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mangagod.dto.data.GenreDataDTO;
import com.mangagod.dto.request.GenreRequestDTO;
import com.mangagod.entity.GenreEntity;

@Component
public class GenreMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public GenreDataDTO mapEntityToDataDTO(GenreEntity entity) {
		GenreDataDTO dataDTO = this.modelMapper.map(entity, GenreDataDTO.class);
		return dataDTO;
	}
	
	public GenreEntity mapRequestToEntity(GenreRequestDTO requestDTO) {
		GenreEntity entity = this.modelMapper.map(requestDTO, GenreEntity.class);
		return entity;
	}
	
}