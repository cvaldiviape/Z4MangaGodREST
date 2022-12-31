package com.mangagod.service.entitygraph;

import com.mangagod.dto.criteria.request.StoryByCountryRequestDTO;
import com.mangagod.dto.criteria.request.StoryByGenreRequestDTO;
import com.mangagod.dto.criteria.request.StoryByMangakaRequestDTO;
import com.mangagod.dto.criteria.request.StoryTitleRequestDTO;
import com.mangagod.dto.criteria.response.StoryGenresManyToManyResponseDTO;
import com.mangagod.dto.criteria.response.StoryJoinResponseDTO;
import com.mangagod.dto.criteria.response.StoryMangakasResponseDTO;
import com.mangagod.dto.criteria.response.StorySomeFieldsResponseDTO;
import com.mangagod.dto.entitygraph.response.StoryTestResponseDTO;
import com.mangagod.dto.response.StoryResponseDTO;
import com.mangagod.entity.StoryEntity;

import java.util.List;

public interface StoryServiceGraph {

    List<StorySomeFieldsResponseDTO> testEntityGrahpCriteria();
    StoryResponseDTO findByPrice(Double price);

    StoryTestResponseDTO findByPriceCriteria(Double price);
}