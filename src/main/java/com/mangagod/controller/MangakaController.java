package com.mangagod.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mangagod.dto.data.MangakaDataDTO;
import com.mangagod.dto.pagination.MangakaAllPageableDataDTO;
import com.mangagod.dto.request.MangakaRequestDTO;
import com.mangagod.dto.response.MainResponse;
import com.mangagod.service.MangakaService;
import com.mangagod.util.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("mangaka")
public class MangakaController {
	
	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private MangakaService mangakaService;
	
	// ---------------------------------------------------------- controllers ----------------------------------------------------------- //
	@ApiOperation("Esta operación se encarga de listar a todos los mangakas.")
	@PreAuthorize("hasRole('ADMIN')") 
	@GetMapping
	public ResponseEntity<MainResponse> getAllMangakas(@RequestParam(value = "numberPage", defaultValue = AppConstants.NUM_PAGE_DEFAULT, required = false) int numberPage,
	      											   @RequestParam(value = "sizePage", defaultValue = AppConstants.SIZE_PAGE_DEFAULT, required = false) int sizePage,
	      											   @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_DEFAULT, required = false) String sortBy,
	      											   @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_DEFAULT, required = false) String sortDir){
		MangakaAllPageableDataDTO genreAllPageableDataDTO = this.mangakaService.getAll(numberPage, sizePage, sortBy, sortDir);
		MainResponse mainResponse = new MainResponse(true, "Lista de mangakas.", genreAllPageableDataDTO);
		return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
	}
	
	@ApiOperation("Esta operación se encarga de obtener los datos de un mangaka en base a su ID.")
	@PreAuthorize("hasRole('ADMIN')") 
	@GetMapping("/{mangaka_id}")
	public ResponseEntity<MainResponse> getMangakaById(@PathVariable (name = "mangaka_id") int mangakaId){
		MangakaDataDTO dataDTO = this.mangakaService.getById(mangakaId);
		MainResponse mainResponse = new MainResponse(true, "Obteniendo mangaka por ID.", dataDTO);
		return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
	}

	@ApiOperation("Esta operación se encarga de crear un nuevo mangaka.")
	@PreAuthorize("hasRole('ADMIN')") 
	@PostMapping
	public ResponseEntity<MainResponse> createMangaka(@Valid @RequestBody MangakaRequestDTO requestDTO){
		MangakaDataDTO dataDTO = this.mangakaService.create(requestDTO); 
		MainResponse mainResponse = new MainResponse(true, "El mangaka ha sido creado exitosamente!", dataDTO);
		return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
	}
}
