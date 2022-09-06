package com.mangagod.dto.pagination;

import java.util.List;
import com.mangagod.dto.data.JobDataDTO;
import com.mangagod.dto.pagination.base.PageableDataDTOImpl;

public class JobAllPageableDataDTO extends PageableDataDTOImpl {

private List<JobDataDTO> jobs;
	
	public JobAllPageableDataDTO() {
		
	}

	public List<JobDataDTO> getGenres() {
		return jobs;
	}

	public void setGenres(List<JobDataDTO> jobs) {
		this.jobs = jobs;
	}
	
}
