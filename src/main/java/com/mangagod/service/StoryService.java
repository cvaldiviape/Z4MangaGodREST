package com.mangagod.service;

import com.mangagod.dto.data.StoryDataDTO;
import com.mangagod.dto.pagination.StoryAllPageableDataDTO;
import com.mangagod.dto.request.StoryRequestDTO;
import com.mangagod.service.base.BaseService;

public interface StoryService extends BaseService<StoryAllPageableDataDTO, StoryDataDTO, StoryRequestDTO, Integer>{
	
}