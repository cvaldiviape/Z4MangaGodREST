package com.mangagod.controller;

import javax.validation.Valid;
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
import com.mangagod.dto.data.ProfessionDataDTO;
import com.mangagod.dto.pagination.ProfessionAllPageableDataDTO;
import com.mangagod.dto.request.ProfessionRequestDTO;
import com.mangagod.dto.response.MainResponse;
import com.mangagod.service.ProfessionService;
import com.mangagod.util.AppConstants;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("profession")
public class ProfessionController {

	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private ProfessionService professionService;
	
	// ---------------------------------------------------------- controllers ----------------------------------------------------------- //
	@ApiOperation("Esta operacion se encarga de listar a todas las profesiones.")
	@PreAuthorize("hasRole('ADMIN')") 
	@GetMapping
	public ResponseEntity<MainResponse> getAllProfessions(@RequestParam(value = "numberPage", defaultValue = AppConstants.NUM_PAGE_DEFAULT, required = false) int numberPage,
	      											      @RequestParam(value = "sizePage", defaultValue = AppConstants.SIZE_PAGE_DEFAULT, required = false) int sizePage,
	      											      @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_DEFAULT, required = false) String sortBy,
	      											      @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_DEFAULT, required = false) String sortDir){
		ProfessionAllPageableDataDTO pageableDataDTO = this.professionService.getAll(numberPage, sizePage, sortBy, sortDir);
		MainResponse mainResponse = new MainResponse(true, "Lista de paises.", pageableDataDTO);
		return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
	}
	
	@ApiOperation("Esta operacion se encarga de obtener los datos de un pais en base a su ID.")
	@PreAuthorize("hasRole('ADMIN')") 
	@GetMapping("/{profession_id}")
	public ResponseEntity<MainResponse> getProessionById(@PathVariable (name = "profession_id") int professionId){
		ProfessionDataDTO dataDTO = this.professionService.getById(professionId);
		MainResponse mainResponse = new MainResponse(true, "Obteniendo profesión por ID.", dataDTO);
		return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
	}

	@ApiOperation("Esta operacion se encarga de crear una nueva profesión.")
	@PreAuthorize("hasRole('ADMIN')") 
	@PostMapping
	public ResponseEntity<MainResponse> createProfession(@Valid @RequestBody ProfessionRequestDTO requestDTO){
		ProfessionDataDTO dataDTO = this.professionService.create(requestDTO); 
		MainResponse mainResponse = new MainResponse(true, "La profesión ha sido creado exitosamente!", dataDTO);
		return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
	}

	@ApiOperation("Esta operacion se encarga de actualizar los datos de un pais.")
	@PreAuthorize("hasRole('ADMIN')") 
	@PutMapping("/{profession_id}")
	public ResponseEntity<MainResponse> updateProfession(@PathVariable (name = "profession_id") int professionId, 
													     @Valid @RequestBody ProfessionRequestDTO requestDTO ){
		ProfessionDataDTO dataDTO = this.professionService.update(professionId, requestDTO); 
		MainResponse mainResponse = new MainResponse(true, "La profesión ha sido actualizado exitosamente!", dataDTO);
		return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
	}

	@ApiOperation("Esta operacion se encarga de eliminar un pais en base a su ID.")
	@PreAuthorize("hasRole('ADMIN')") 
	@DeleteMapping("/{profession_id}")
	public ResponseEntity<MainResponse> deleteProfession(@PathVariable (name = "profession_id") int professionId){
		ProfessionDataDTO dataDTO = this.professionService.delete(professionId);
		MainResponse mainResponse = new MainResponse(true, "La profesión ha sido eliminado exitosamente!", dataDTO);
		return new ResponseEntity<MainResponse>(mainResponse, HttpStatus.OK);
	}
	
}