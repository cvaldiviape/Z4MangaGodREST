package com.mangagod.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mangagod.dto.data.GenreDataDTO;
import com.mangagod.dto.pagination.GenreAllPageableDataDTO;
import com.mangagod.dto.request.GenreRequestDTO;
import com.mangagod.entity.GenreEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.GenreMapper;
import com.mangagod.repository.GenreRepository;

@Service
@Transactional
public class GenreServiceImpl implements GenreService{

	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private GenreMapper genreMapper;
	
	// ----------------------------------------------------------- services ----------------------------------------------------------- //
	@Override
	public GenreAllPageableDataDTO getAll(Integer numberPage, Integer sizePage, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending();
	
		// agregando paginación
		Pageable pageable = PageRequest.of(numberPage, sizePage, sort);
		Page<GenreEntity> genresPageable = this.genreRepository.findAll(pageable);	
		List<GenreEntity> genresEntity = genresPageable.getContent();
		List<GenreDataDTO> genressDTO = genresEntity.stream().map((x) -> this.genreMapper.mapEntityToDataDTO(x)).collect(Collectors.toList());	
		
		GenreAllPageableDataDTO pageableDataDTO = new GenreAllPageableDataDTO();
		pageableDataDTO.setGenres(genressDTO);
		pageableDataDTO.setNumberPage(genresPageable.getNumber());
		pageableDataDTO.setSizePage(genresPageable.getSize());
		pageableDataDTO.setTotalElements(genresPageable.getTotalElements());
		pageableDataDTO.setTotalPages(genresPageable.getTotalPages());
		pageableDataDTO.setIsLastPage(genresPageable.isLast());
		
		return pageableDataDTO;
	}

	@Override
	public GenreDataDTO getById(Integer id) {
		// TODO Auto-generated method stub
		GenreEntity entity = this.genreRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Género", "id", id));
		GenreDataDTO dataDTO = this.genreMapper.mapEntityToDataDTO(entity);
		return dataDTO;
	}

	@Override
	public GenreDataDTO create(GenreRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		Boolean existNname = this.genreRepository.existsByName(requestDTO.getName());
		if(existNname) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		GenreEntity entity = this.genreMapper.mapRequestToEntity(requestDTO);
		
		GenreDataDTO dataCreated = this.genreMapper.mapEntityToDataDTO(this.genreRepository.save(entity));			
		return dataCreated;
	}

	@Override
	public GenreDataDTO update(Integer id, GenreRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		GenreEntity dataCurrent = this.genreRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Género", "id", id));
		Boolean existsUsername = this.genreRepository.existsByName(requestDTO.getName());
		Boolean diferentUsernameCurrent = (!requestDTO.getName().equalsIgnoreCase(dataCurrent.getName()));
		if(existsUsername && diferentUsernameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		dataCurrent.setName(requestDTO.getName());
		
		GenreDataDTO dataUpdated = this.genreMapper.mapEntityToDataDTO(this.genreRepository.save(dataCurrent));	
		return dataUpdated;
	}

	@Override
	public GenreDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		GenreEntity entity = this.genreRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Género", "id", id));
		this.genreRepository.delete(entity);
		GenreDataDTO dataDeleted = this.genreMapper.mapEntityToDataDTO(entity);
		return dataDeleted;
	}

}