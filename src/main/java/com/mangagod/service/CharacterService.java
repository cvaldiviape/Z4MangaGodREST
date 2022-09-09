package com.mangagod.service;

import com.mangagod.dto.data.CharacterDataDTO;
import com.mangagod.dto.pagination.CharacterAllPageableDataDTO;
import com.mangagod.dto.request.CharacterRequestDTO;
import com.mangagod.service.base.BaseService;

public interface CharacterService extends BaseService<CharacterAllPageableDataDTO, CharacterDataDTO, CharacterRequestDTO, Integer>{

}