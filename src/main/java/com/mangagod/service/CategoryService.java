package com.mangagod.service;

import com.mangagod.dto.data.CategoryDataDTO;
import com.mangagod.dto.pagination.CategoryAllPageableDataDTO;
import com.mangagod.dto.request.CategoryRequestDTO;
import com.mangagod.service.base.BaseService;

public interface CategoryService extends BaseService<CategoryAllPageableDataDTO, CategoryDataDTO, CategoryRequestDTO, Integer>{

}