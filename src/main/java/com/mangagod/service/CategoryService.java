package com.mangagod.service;

import com.mangagod.dto.pagination.CategoryAllPageableDataDTO;
import com.mangagod.dto.request.CategoryRequestDTO;
import com.mangagod.dto.response.CategoryResponseDTO;
import com.mangagod.service.base.BaseService;

public interface CategoryService extends BaseService<CategoryAllPageableDataDTO, CategoryResponseDTO, CategoryRequestDTO, Integer>{

}