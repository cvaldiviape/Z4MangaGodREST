package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.data.ProfessionDataDTO;
import com.mangagod.dto.pagination.base.PageableDataDTOImpl;

public class ProfessionAllPageableDataDTO extends PageableDataDTOImpl {

	private List<ProfessionDataDTO> professions;
	
	public ProfessionAllPageableDataDTO() {
		
	}

	public List<ProfessionDataDTO> getProfessions() {
		return professions;
	}

	public void setProfessions(List<ProfessionDataDTO> professions) {
		this.professions = professions;
	}

}