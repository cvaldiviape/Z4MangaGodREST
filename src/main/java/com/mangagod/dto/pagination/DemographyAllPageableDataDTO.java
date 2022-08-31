package com.mangagod.dto.pagination;

import java.util.List;
import com.mangagod.dto.data.DemographyDataDTO;
import com.mangagod.dto.pagination.base.PageableDataDTOImpl;

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