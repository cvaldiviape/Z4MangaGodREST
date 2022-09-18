package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.pagination.base.PageableDataDTOImpl;
import com.mangagod.dto.response.CountryResponseDTO;

public class CountryAllPageableDataDTO extends PageableDataDTOImpl {

	private List<CountryResponseDTO> countries;
	
	public CountryAllPageableDataDTO() {
		
	}

	public List<CountryResponseDTO> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryResponseDTO> countries) {
		this.countries = countries;
	}

}