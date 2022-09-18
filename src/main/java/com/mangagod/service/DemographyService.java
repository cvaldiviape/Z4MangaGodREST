package com.mangagod.service;

import com.mangagod.dto.pagination.DemographyAllPageableDataDTO;
import com.mangagod.dto.request.DemographyRequestDTO;
import com.mangagod.dto.response.DemographyResponseDTO;
import com.mangagod.service.base.BaseService;

public interface DemographyService extends BaseService<DemographyAllPageableDataDTO, DemographyResponseDTO, DemographyRequestDTO, Integer>{
	
}