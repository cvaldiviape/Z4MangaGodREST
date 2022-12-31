package com.mangagod.mapper;

import com.mangagod.dto.criteria.response.StoryMangakasResponseDTO;
import com.mangagod.dto.request.StoryRequestDTO;
import com.mangagod.dto.response.StoryResponseDTO;
import com.mangagod.dto.response.view.StoryViewResponseDTO;
import com.mangagod.entity.StoryEntity;
import com.mangagod.entity.StoryMangakaEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoryMangakasMapper {
    @Autowired
    private ModelMapper modelMapper;

    // ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
    public StoryMangakasResponseDTO mapEntityToResponseDTO(StoryMangakaEntity entity) {
        return this.modelMapper.map(entity, StoryMangakasResponseDTO.class);
    }

//    public StoryViewResponseDTO mapEntityToViewResponseDTO(StoryEntity entity) {
//        return this.modelMapper.map(entity, StoryViewResponseDTO.class);
//    }
//
//    public StoryEntity mapRequestToEntity(StoryRequestDTO requestDTO) {
//        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        return this.modelMapper.map(requestDTO, StoryEntity.class);
//    }

}
