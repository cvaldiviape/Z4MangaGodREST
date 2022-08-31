package com.mangagod.service;

import com.mangagod.dto.data.GenreDataDTO;
import com.mangagod.dto.pagination.GenreAllPageableDataDTO;
import com.mangagod.dto.request.GenreRequestDTO;
import com.mangagod.service.base.BaseService;

public interface GenreService extends BaseService<GenreAllPageableDataDTO, GenreDataDTO, GenreRequestDTO, Integer>{

}