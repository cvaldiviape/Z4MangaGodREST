package com.mangagod.service;

import com.mangagod.dto.data.DemographyDataDTO;
import com.mangagod.dto.pagination.DemographyAllPageableDataDTO;
import com.mangagod.dto.request.DemographyRequestDTO;
import com.mangagod.service.base.BaseService;

public interface DemographyService extends BaseService<DemographyAllPageableDataDTO, DemographyDataDTO, DemographyRequestDTO, Integer>{
	
}