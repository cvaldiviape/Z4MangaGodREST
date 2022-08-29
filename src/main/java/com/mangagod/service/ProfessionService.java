package com.mangagod.service;

import com.mangagod.dto.data.ProfessionAllPageableDataDTO;
import com.mangagod.dto.data.ProfessionDataDTO;
import com.mangagod.dto.request.ProfessionCreateRequestDTO;
import com.mangagod.dto.request.ProfessionUpdateRequestDTO;
import com.mangagod.service.generic.CrudService;

public interface ProfessionService extends CrudService<ProfessionAllPageableDataDTO, ProfessionDataDTO, ProfessionCreateRequestDTO, ProfessionUpdateRequestDTO>{

}