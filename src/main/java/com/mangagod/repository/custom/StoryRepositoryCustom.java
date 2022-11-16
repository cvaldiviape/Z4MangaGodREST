package com.mangagod.repository.custom;

import com.mangagod.dto.request.search.StoryRequestSearchDTO;
import com.mangagod.dto.response.criteria.StoryViewCriteriaResponse;
import com.mangagod.entity.StoryEntity;

import java.util.List;

public interface StoryRepositoryCustom {

    List<StoryViewCriteriaResponse> findAllBySearch(StoryRequestSearchDTO ordenVentaDto);

}
