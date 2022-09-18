package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.pagination.base.PageableDataDTOImpl;
import com.mangagod.dto.response.JobResponseDTO;

public class JobAllPageableDataDTO extends PageableDataDTOImpl {

private List<JobResponseDTO> jobs;
	
	public JobAllPageableDataDTO() {
		
	}

	public List<JobResponseDTO> getGenres() {
		return jobs;
	}

	public void setGenres(List<JobResponseDTO> jobs) {
		this.jobs = jobs;
	}
	
}
