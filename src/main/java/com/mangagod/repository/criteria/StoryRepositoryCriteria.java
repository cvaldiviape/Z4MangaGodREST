package com.mangagod.repository.criteria;

import com.mangagod.dto.criteria.request.StoryByCountryRequestDTO;
import com.mangagod.dto.criteria.request.StoryByGenreRequestDTO;
import com.mangagod.dto.criteria.request.StoryByMangakaRequestDTO;
import com.mangagod.dto.criteria.response.StoryJoinResponseDTO;
import com.mangagod.dto.criteria.request.StoryTitleRequestDTO;
import com.mangagod.dto.criteria.response.StoryGenresManyToManyResponseDTO;
import com.mangagod.dto.criteria.response.StoryMangakasResponseDTO;
import com.mangagod.dto.criteria.response.StorySomeFieldsResponseDTO;
import com.mangagod.dto.response.StoryResponseDTO;

import java.util.List;

public interface StoryRepositoryCriteria {

    List<StoryResponseDTO> findAllSimple();
    List<StorySomeFieldsResponseDTO> findAllByOnlySomeFields();
    List<StorySomeFieldsResponseDTO> findAllByTitle(StoryTitleRequestDTO storyTitleRequestDTO);
    List<StorySomeFieldsResponseDTO> findAllByTitleOrderByYear(StoryTitleRequestDTO storyTitleRequestDTO);
    List<StoryJoinResponseDTO> findAllWithJoin(StoryByCountryRequestDTO storyByCountryRequestDTO);
    List<StoryGenresManyToManyResponseDTO> findAllWithJoinGenres(StoryByGenreRequestDTO storyByGenreRequestDTO);
    List<StoryMangakasResponseDTO> findAllWithJoinMangaka(StoryByMangakaRequestDTO storyByMangakaRequestDTO);

}