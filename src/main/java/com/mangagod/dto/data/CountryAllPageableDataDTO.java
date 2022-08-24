package com.mangagod.dto.data;

import java.util.List;

import com.mangagod.dto.data.pagination.PageableDataDTOImpl;

public class CountryAllPageableDataDTO extends PageableDataDTOImpl {

	private List<CountryDataDTO> countries;
	
	public CountryAllPageableDataDTO() {
		
	}

	public List<CountryDataDTO> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryDataDTO> countries) {
		this.countries = countries;
	}

}