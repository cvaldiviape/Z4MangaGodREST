package com.mangagod.service;

import com.mangagod.dto.data.GenreAllPageableDataDTO;
import com.mangagod.dto.data.GenreDataDTO;
import com.mangagod.dto.request.GenreCreateRequestDTO;
import com.mangagod.dto.request.GenreUpdateRequestDTO;

public interface GenreService extends CrudService<GenreAllPageableDataDTO, GenreDataDTO, GenreCreateRequestDTO, GenreUpdateRequestDTO>{

}
