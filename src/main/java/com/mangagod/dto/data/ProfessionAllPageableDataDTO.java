package com.mangagod.dto.data;

import java.util.List;
import com.mangagod.dto.data.pagination.PageableDataDTOImpl;

public class ProfessionAllPageableDataDTO extends PageableDataDTOImpl {

	private List<ProfessionDataDTO> professions;
	
	public ProfessionAllPageableDataDTO() {
		
	}

	public List<ProfessionDataDTO> getCountries() {
		return professions;
	}

	public void setCountries(List<ProfessionDataDTO> professions) {
		this.professions = professions;
	}

}