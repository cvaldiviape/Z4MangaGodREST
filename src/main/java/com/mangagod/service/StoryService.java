package com.mangagod.service;

import com.mangagod.dto.request.StoryRequestDTO;
import com.mangagod.dto.request.search.StoryRequestSearchDTO;
import com.mangagod.dto.response.StoryResponseDTO;
import com.mangagod.dto.response.criteria.StoryViewCriteriaResponse;
import com.mangagod.dto.response.page.StoriesPageResponseDTO;
import com.mangagod.service.base.BaseService;

import java.util.List;

public interface StoryService extends BaseService<StoriesPageResponseDTO, StoryResponseDTO, StoryRequestDTO, Integer>{

    List<StoryViewCriteriaResponse> findAllBySearch(StoryRequestSearchDTO ordenVentaDto);

}