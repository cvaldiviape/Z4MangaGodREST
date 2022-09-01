package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mangagod.dto.data.CountryDataDTO;
import com.mangagod.dto.request.CountryRequestDTO;
import com.mangagod.entity.CountryEntity;

@Component
public class CountryMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public CountryDataDTO mapEntityToDataDTO(CountryEntity entity) {
		CountryDataDTO dataDTO = this.modelMapper.map(entity, CountryDataDTO.class);
		return dataDTO;
	}
	
	public CountryEntity mapRequestToEntity(CountryRequestDTO requestDTO) {
		CountryEntity entity = this.modelMapper.map(requestDTO, CountryEntity.class);
		return entity;
	}
		
}