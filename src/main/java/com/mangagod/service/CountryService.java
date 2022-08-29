package com.mangagod.service;

import com.mangagod.dto.data.CountryAllPageableDataDTO;
import com.mangagod.dto.data.CountryDataDTO;
import com.mangagod.dto.request.CountryCreateRequestDTO;
import com.mangagod.dto.request.CountryUpdateRequestDTO;
import com.mangagod.service.generic.CrudService;

public interface CountryService extends CrudService<CountryAllPageableDataDTO, CountryDataDTO, CountryCreateRequestDTO, CountryUpdateRequestDTO>{

}