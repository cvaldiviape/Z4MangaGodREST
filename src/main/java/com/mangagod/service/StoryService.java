package com.mangagod.service;

import com.mangagod.dto.pagination.StoryAllPageableDataDTO;
import com.mangagod.dto.request.StoryRequestDTO;
import com.mangagod.dto.response.StoryResponseDTO;
import com.mangagod.service.base.BaseService;

public interface StoryService extends BaseService<StoryAllPageableDataDTO, StoryResponseDTO, StoryRequestDTO, Integer>{
	
}