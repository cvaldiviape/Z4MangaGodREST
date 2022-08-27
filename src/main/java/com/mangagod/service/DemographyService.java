package com.mangagod.service;

import com.mangagod.dto.data.DemographyAllPageableDataDTO;
import com.mangagod.dto.data.DemographyDataDTO;
import com.mangagod.dto.request.DemographyCreateRequestDTO;
import com.mangagod.dto.request.DemographyUpdateRequestDTO;

public interface DemographyService extends CrudService<DemographyAllPageableDataDTO, DemographyDataDTO, DemographyCreateRequestDTO, DemographyUpdateRequestDTO>{
	
}