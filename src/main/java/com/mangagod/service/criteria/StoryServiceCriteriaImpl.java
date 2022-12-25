package com.mangagod.service.criteria;

import com.mangagod.dto.criteria.request.StoryByCountryRequestDTO;
import com.mangagod.dto.criteria.request.StoryByGenreRequestDTO;
import com.mangagod.dto.criteria.request.StoryByMangakaRequestDTO;
import com.mangagod.dto.criteria.response.StoryJoinResponseDTO;
import com.mangagod.dto.criteria.request.StoryTitleRequestDTO;
import com.mangagod.dto.criteria.response.StoryGenresManyToManyResponseDTO;
import com.mangagod.dto.criteria.response.StoryMangakasResponseDTO;
import com.mangagod.dto.criteria.response.StorySomeFieldsResponseDTO;
import com.mangagod.dto.response.StoryResponseDTO;
import com.mangagod.repository.criteria.StoryRepositoryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StoryServiceCriteriaImpl implements StoryServiceCriteria  {
    @Autowired
    private StoryRepositoryCriteria storyRepositoryCriteria;

    @Override
    public List<StoryResponseDTO> findAllSimple() {
        return this.storyRepositoryCriteria.findAllSimple();
    }

    @Override
    public List<StorySomeFieldsResponseDTO> findAllByOnlySomeFields() {
        return this.storyRepositoryCriteria.findAllByOnlySomeFields();
    }

    @Override
    public List<StorySomeFieldsResponseDTO> findAllByTitle(StoryTitleRequestDTO requestDTO) {
        return this.storyRepositoryCriteria.findAllByTitle(requestDTO);
    }

    @Override
    public List<StorySomeFieldsResponseDTO> findAllByTitleOrderByYear(StoryTitleRequestDTO requestDTO) {
        return this.storyRepositoryCriteria.findAllByTitleOrderByYear(requestDTO);
    }

    @Override
    public List<StoryJoinResponseDTO> findAllWithJoin(StoryByCountryRequestDTO requestDTO) {
        return this.storyRepositoryCriteria.findAllWithJoin(requestDTO);
    }

    @Override
    public List<StoryGenresManyToManyResponseDTO> findAllWithJoinGenres(StoryByGenreRequestDTO requestDTO) {
        return this.storyRepositoryCriteria.findAllWithJoinGenres(requestDTO);
    }

    @Override
    public List<StoryMangakasResponseDTO> findAllWithJoinMangaka(StoryByMangakaRequestDTO requestDTO) {
        return this.storyRepositoryCriteria.findAllWithJoinMangaka(requestDTO);
    }
}
