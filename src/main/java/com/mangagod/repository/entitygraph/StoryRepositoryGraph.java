package com.mangagod.repository.entitygraph;

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

import java.util.List;

public interface StoryRepositoryGraph {

    List<StorySomeFieldsResponseDTO> testEntityGrahpCriteria();

    StoryTestResponseDTO findByPriceCriteria(Double price);
    
}