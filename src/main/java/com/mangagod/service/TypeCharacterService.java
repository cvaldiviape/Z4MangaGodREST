package com.mangagod.service;

import com.mangagod.dto.data.TypeCharacterAllPageableDataDTO;
import com.mangagod.dto.data.TypeCharacterDataDTO;
import com.mangagod.dto.request.TypeCharacterCreateRequestDTO;
import com.mangagod.dto.request.TypeCharacterUpdateRequestDTO;
import com.mangagod.service.generic.CrudService;

public interface TypeCharacterService  extends CrudService<TypeCharacterAllPageableDataDTO, TypeCharacterDataDTO, TypeCharacterCreateRequestDTO, TypeCharacterUpdateRequestDTO>{

}