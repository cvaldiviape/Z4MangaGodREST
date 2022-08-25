package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mangagod.dto.data.GenreDataDTO;
import com.mangagod.dto.request.GenreCreateRequestDTO;
import com.mangagod.entity.GenreEntity;

@Component
public class GenreMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public GenreDataDTO mapGenreEntityToGenreDataDTO(GenreEntity genreEntity) {
		GenreDataDTO genreDataDTO = this.modelMapper.map(genreEntity, GenreDataDTO.class);
		return genreDataDTO;
	}
	
	public GenreEntity mapGenreCreateRequestToGenreEntity(GenreCreateRequestDTO genreCreateRequestDTO) {
		genreCreateRequestDTO.setName(genreCreateRequestDTO.getName().trim());
		GenreEntity genreEntity = this.modelMapper.map(genreCreateRequestDTO, GenreEntity.class);
		return genreEntity;
	}
	
}