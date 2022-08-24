package com.mangagod.service;

import com.mangagod.dto.data.CountryAllPageableDataDTO;
import com.mangagod.dto.data.CountryDataDTO;
import com.mangagod.dto.request.CountryCreateRequestDTO;
import com.mangagod.dto.request.CountryUpdateRequestDTO;

public interface CountryService extends ServiceCrud<CountryAllPageableDataDTO, CountryDataDTO, CountryCreateRequestDTO, CountryUpdateRequestDTO>{

}
