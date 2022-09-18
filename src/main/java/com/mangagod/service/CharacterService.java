package com.mangagod.service;

import com.mangagod.dto.pagination.CharacterAllPageableDataDTO;
import com.mangagod.dto.request.CharacterRequestDTO;
import com.mangagod.dto.response.CharacterResponseDTO;
import com.mangagod.service.base.BaseService;

public interface CharacterService extends BaseService<CharacterAllPageableDataDTO, CharacterResponseDTO, CharacterRequestDTO, Integer>{

}