package com.mangagod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mangagod.dto.data.GenreAllPageableDataDTO;
import com.mangagod.dto.data.GenreDataDTO;
import com.mangagod.dto.request.GenreCreateRequestDTO;
import com.mangagod.dto.request.GenreUpdateRequestDTO;
import com.mangagod.dto.response.MainResponse;
import com.mangagod.service.GenreService;
import com.mangagod.util.AppConstants;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("genre")
public class GenreController {

	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private GenreService genreService;
	
	// ---------------------------------------------------------- controllers ----------------------------------------------------------- //
	@ApiOperation("Esta operacion se encarga de listar a todos los géneros.")
	@PreAuthorize("hasRole('ADMIN')") 
	@GetMapping
	public ResponseEntity<MainResponse> getAllGenres(@RequestParam(value = "numberPage", defaultValue = AppConstants.NUM_PAGE_DEFAULT, required = false) int numberPage,
	      											    @RequestParam(value = "sizePage", defaultValue = AppConstants.SIZE_PAGE_DEFAULT, required = false) int sizePage,
	      											    @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_DEFAULT, required = false) String sortBy,
	      											    @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_DEFAULT, required = false) String sortDir){
		GenreAllPageableDataDTO genreAllPageableDataDTO = this.genreService.getAll(numberPage, sizePage, sortBy, sortDir);
		MainResponse mainResponse = new MainResponse(true, "Lista de géneros.", genreAllPageableDataDTO);
		return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
	}
	
	@ApiOperation("Esta operacion se encarga de obtener los datos de un género en base a su ID.")
	@PreAuthorize("hasRole('ADMIN')") 
	@GetMapping("/{genre_id}")
	public ResponseEntity<MainResponse> getGenreById(@PathVariable (name = "genre_id") int genreId){
		GenreDataDTO genreDataDTO = this.genreService.getById(genreId);
		MainResponse mainResponse = new MainResponse(true, "Obteniendo género por ID.", genreDataDTO);
		return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
	}

	@ApiOperation("Esta operacion se encarga de crear un nuevo género.")
	@PreAuthorize("hasRole('ADMIN')") 
	@PostMapping
	public ResponseEntity<MainResponse> createCountry(@RequestBody GenreCreateRequestDTO genreCreateRequestDTO){
		GenreDataDTO genreDataDTO = this.genreService.create(genreCreateRequestDTO); 
		MainResponse mainResponse = new MainResponse(true, "El género ha sido creado exitosamente!", genreDataDTO);
		return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
	}

	@ApiOperation("Esta operacion se encarga de actualizar los datos de un género.")
	@PreAuthorize("hasRole('ADMIN')") 
	@PutMapping("/{genre_id}")
	public ResponseEntity<MainResponse> updateCountry(@PathVariable (name = "genre_id") int genreId, 
			                                          @RequestBody GenreUpdateRequestDTO genreUpdateRequestDTO ){
		GenreDataDTO genreDataDTO = this.genreService.update(genreId, genreUpdateRequestDTO); 
		MainResponse mainResponse = new MainResponse(true, "El género ha sido actualizado exitosamente!", genreDataDTO);
		return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
	}

	@ApiOperation("Esta operacion se encarga de eliminar a un género en base a su ID.")
	@PreAuthorize("hasRole('ADMIN')") 
	@DeleteMapping("/{genre_id}")
	public ResponseEntity<MainResponse> deleteCountry(@PathVariable (name = "genre_id") int genreId){
		GenreDataDTO genreDataDTO = this.genreService.delete(genreId);
		MainResponse mainResponse = new MainResponse(true, "El género ha sido eliminado exitosamente!", genreDataDTO);
		return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
	}
		
}
