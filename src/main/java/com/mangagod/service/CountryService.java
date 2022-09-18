package com.mangagod.service;

import com.mangagod.dto.pagination.CountryAllPageableDataDTO;
import com.mangagod.dto.request.CountryRequestDTO;
import com.mangagod.dto.response.CountryResponseDTO;
import com.mangagod.service.base.BaseService;

public interface CountryService extends BaseService<CountryAllPageableDataDTO, CountryResponseDTO, CountryRequestDTO, Integer>{

}