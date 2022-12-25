package com.mangagod.controller.criteria;

import com.mangagod.dto.criteria.request.StoryByGenreRequestDTO;
import com.mangagod.dto.criteria.request.StoryByMangakaRequestDTO;
import com.mangagod.dto.criteria.request.StoryTitleRequestDTO;
import com.mangagod.dto.criteria.response.StoryGenresManyToManyResponseDTO;
import com.mangagod.dto.criteria.response.StoryMangakasResponseDTO;
import com.mangagod.dto.response.MainResponse;
import com.mangagod.dto.response.StoryResponseDTO;
import com.mangagod.dto.criteria.request.StoryByCountryRequestDTO;
import com.mangagod.dto.criteria.response.StoryJoinResponseDTO;
import com.mangagod.dto.criteria.response.StorySomeFieldsResponseDTO;
import com.mangagod.service.criteria.StoryServiceCriteriaImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("story-criteria")
public class StoryCriteriaController {

    @Autowired
    StoryServiceCriteriaImpl storyServiceCriteria;

    @ApiOperation("Busqueda simple")
    @PostMapping("/01-find-all-simple")
    public ResponseEntity<MainResponse> findAllSimple(){
        List<StoryResponseDTO> dataDTO = this.storyServiceCriteria.findAllSimple();
        MainResponse mainResponse = new MainResponse(true, "Busqueda simple", dataDTO);
        return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
    }

    @ApiOperation("Busqueda, pero solo consultar ciertos campos")
    @PostMapping("/02-find-all-some-fields")
    public ResponseEntity<MainResponse> findAllByOnlySomeFields( ){
        List<StorySomeFieldsResponseDTO> dataDTO = this.storyServiceCriteria.findAllByOnlySomeFields();
        MainResponse mainResponse = new MainResponse(true, "Busqueda, pero solo consultar ciertos campos", dataDTO);
        return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
    }

    @ApiOperation("Busqueda por titulo")
    @PostMapping("/03-find-all-by-title")
    public ResponseEntity<MainResponse> findAllByTitle(@RequestBody StoryTitleRequestDTO requestDTO){
        List<StorySomeFieldsResponseDTO> dataDTO = this.storyServiceCriteria.findAllByTitle(requestDTO);
        MainResponse mainResponse = new MainResponse(true, "Busqueda por titulo", dataDTO);
        return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
    }

    @ApiOperation("Busqueda por titulo y ordenadon por año")
    @PostMapping("/04-find-all-by-title-order-by-year")
    public ResponseEntity<MainResponse> findAllByTitleOrderByYear(@RequestBody StoryTitleRequestDTO requestDTO){
        List<StorySomeFieldsResponseDTO> dataDTO = this.storyServiceCriteria.findAllByTitleOrderByYear(requestDTO);
        MainResponse mainResponse = new MainResponse(true, "Busqueda por titulo y ordenadon por año", dataDTO);
        return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
    }

    @ApiOperation("Busqueda con multiples tablas relacionadas a Story")
    @PostMapping("/05-find-all-join")
    public ResponseEntity<MainResponse> findAllWithJoin(@RequestBody StoryByCountryRequestDTO requestDTO){
        List<StoryJoinResponseDTO> dataDTO = this.storyServiceCriteria.findAllWithJoin(requestDTO);
        MainResponse mainResponse = new MainResponse(true, "Busqueda con multiples tablas relacionadas a Story", dataDTO);
        return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
    }

    @ApiOperation("Busqueda con tabla genero de relacion N:M")
    @PostMapping("/06-find-all-with-join-genres")
    public ResponseEntity<MainResponse> findAllWithJoinGenres(@RequestBody StoryByGenreRequestDTO requestDTO){
        List<StoryGenresManyToManyResponseDTO> dataDTO = this.storyServiceCriteria.findAllWithJoinGenres(requestDTO);
        MainResponse mainResponse = new MainResponse(true, "Busqueda con tabla genero de relacion N:M", dataDTO);
        return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
    }

    @ApiOperation("Busqueda historietas por nombre de mangaka")
    @PostMapping("/07-find-all-with-join-mangaka")
    public ResponseEntity<MainResponse> findAllWithJoinMangaka(@RequestBody StoryByMangakaRequestDTO requestDTO){
        List<StoryMangakasResponseDTO> dataDTO = this.storyServiceCriteria.findAllWithJoinMangaka(requestDTO);
        MainResponse mainResponse = new MainResponse(true, "Busqueda historietas por nombre de mangaka", dataDTO);
        return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
    }
}