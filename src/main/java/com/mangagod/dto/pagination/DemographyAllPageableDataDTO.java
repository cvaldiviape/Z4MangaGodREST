package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.pagination.base.PageableDataDTOImpl;
import com.mangagod.dto.response.DemographyResponseDTO;

public class DemographyAllPageableDataDTO extends PageableDataDTOImpl {

	private List<DemographyResponseDTO> demogrhapies;
	
	public DemographyAllPageableDataDTO() {
		
	}

	public List<DemographyResponseDTO> getDemogrhapies() {
		return demogrhapies;
	}

	public void setDemogrhapies(List<DemographyResponseDTO> demogrhapies) {
		this.demogrhapies = demogrhapies;
	}

}