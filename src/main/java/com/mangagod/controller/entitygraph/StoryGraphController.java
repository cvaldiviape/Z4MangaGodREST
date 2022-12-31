package com.mangagod.controller.entitygraph;

import com.mangagod.dto.criteria.request.StoryByCountryRequestDTO;
import com.mangagod.dto.criteria.request.StoryByGenreRequestDTO;
import com.mangagod.dto.criteria.request.StoryByMangakaRequestDTO;
import com.mangagod.dto.criteria.request.StoryTitleRequestDTO;
import com.mangagod.dto.criteria.response.StoryGenresManyToManyResponseDTO;
import com.mangagod.dto.criteria.response.StoryJoinResponseDTO;
import com.mangagod.dto.criteria.response.StoryMangakasResponseDTO;
import com.mangagod.dto.criteria.response.StorySomeFieldsResponseDTO;
import com.mangagod.dto.response.MainResponse;
import com.mangagod.dto.response.StoryResponseDTO;
import com.mangagod.entity.StoryEntity;
import com.mangagod.service.entitygraph.StoryServiceGraph;
import com.mangagod.service.entitygraph.StoryServiceGraphImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("story-grahp")
public class StoryGraphController {

    @Autowired
    StoryServiceGraph storyServiceGrahp;

    @ApiOperation("Test entity grahp con criteria")
    @PostMapping("/test-entitygrahp-criteria")
    public ResponseEntity<MainResponse> testEntityGrahpCriteria(){
        List<StorySomeFieldsResponseDTO> dataDTO = this.storyServiceGrahp.testEntityGrahpCriteria();  // con criteria
        MainResponse mainResponse = new MainResponse(true, "Test entity grahp con criteria", null);
        return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
    }

    @PostMapping("/find-by-price")
    public ResponseEntity<MainResponse> findByPrice(){
        storyServiceGrahp.findByPrice(50d); // retonar un null, por eso lo coloco asi, para ver la query en el en la consola.
        MainResponse mainResponse = new MainResponse(true, "", null);
        return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
    }

    @PostMapping("/find-by-price-criteria")
    public ResponseEntity<MainResponse> findByPriceCriteria(){
        storyServiceGrahp.findByPriceCriteria(50d);
        MainResponse mainResponse = new MainResponse(true, "", null);
        return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
    }

}