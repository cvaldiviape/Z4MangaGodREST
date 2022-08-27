package com.mangagod.dto.data;

import java.util.List;
import com.mangagod.dto.data.pagination.PageableDataDTOImpl;

public class DemographyAllPageableDataDTO extends PageableDataDTOImpl {

	private List<DemographyDataDTO> demogrhapies;
	
	public DemographyAllPageableDataDTO() {
		
	}

	public List<DemographyDataDTO> getDemogrhapies() {
		return demogrhapies;
	}

	public void setDemogrhapies(List<DemographyDataDTO> demogrhapies) {
		this.demogrhapies = demogrhapies;
	}

}