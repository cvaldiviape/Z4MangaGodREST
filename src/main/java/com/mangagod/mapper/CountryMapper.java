package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mangagod.dto.data.CountryDataDTO;
import com.mangagod.dto.request.CountryCreateRequestDTO;
import com.mangagod.entity.CountryEntity;

@Component
public class CountryMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public CountryDataDTO mapCountryEntityToCountryDataDTO(CountryEntity countryEntity) {
		CountryDataDTO countryDataDTO = this.modelMapper.map(countryEntity, CountryDataDTO.class);
		return countryDataDTO;
	}
	
	public CountryEntity mapCountryCreateRequestToCountryEntity(CountryCreateRequestDTO countryCreateRequestDTO) {
		countryCreateRequestDTO.setName(countryCreateRequestDTO.getName().trim());
		CountryEntity countryEntity = this.modelMapper.map(countryCreateRequestDTO, CountryEntity.class);
		return countryEntity;
	}
		
}