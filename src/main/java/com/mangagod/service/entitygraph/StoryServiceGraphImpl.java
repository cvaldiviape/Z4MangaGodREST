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
import com.mangagod.entity.StoryMangakaEntity;
import com.mangagod.mapper.StoryMangakasMapper;
import com.mangagod.mapper.StoryMapper;
import com.mangagod.repository.StoryRepository;
import com.mangagod.repository.entitygraph.StoryRepositoryGraph;
import com.mangagod.repository.entitygraph.StoryRepositoryGraphImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoryServiceGraphImpl implements StoryServiceGraph {

    private final StoryRepositoryGraph storyRepositoryGrahp;
    private final StoryMangakasMapper storyMangakasMapper;

    @Autowired
    private StoryRepository storyRepository;

    @Override
    public List<StorySomeFieldsResponseDTO> testEntityGrahpCriteria() {
        return this.storyRepositoryGrahp.testEntityGrahpCriteria();
    }

    @Override
    public StoryResponseDTO findByPrice(Double price) {
        this.storyRepository.findByPrice(price);
        return null;
    }

    public StoryTestResponseDTO findByPriceCriteria(Double price) {
        this.storyRepositoryGrahp.findByPriceCriteria(price);
        return null;
    }

}