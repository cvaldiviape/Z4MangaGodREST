package com.mangagod.service;

import java.time.LocalDateTime;
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
import com.mangagod.dto.data.GenreAllPageableDataDTO;
import com.mangagod.dto.data.GenreDataDTO;
import com.mangagod.dto.request.GenreCreateRequestDTO;
import com.mangagod.dto.request.GenreUpdateRequestDTO;
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
		List<GenreDataDTO> genressDTO = genresEntity.stream().map(genre -> this.genreMapper.mapGenreEntityToGenreDataDTO(genre)).collect(Collectors.toList());	
		
		GenreAllPageableDataDTO genreAllPageableDataDTO = new GenreAllPageableDataDTO();
		genreAllPageableDataDTO.setGenres(genressDTO);
		genreAllPageableDataDTO.setNumberPage(genresPageable.getNumber());
		genreAllPageableDataDTO.setSizePage(genresPageable.getSize());
		genreAllPageableDataDTO.setTotalElements(genresPageable.getTotalElements());
		genreAllPageableDataDTO.setTotalPages(genresPageable.getTotalPages());
		genreAllPageableDataDTO.setIsLastPage(genresPageable.isLast());
		
		return genreAllPageableDataDTO;
	}

	@Override
	public GenreDataDTO getById(Integer id) {
		// TODO Auto-generated method stub
		GenreEntity genreEntity = this.genreRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Género", "id", id));
		GenreDataDTO genreDataDTO = this.genreMapper.mapGenreEntityToGenreDataDTO(genreEntity);
		return genreDataDTO;
	}

	@Override
	public GenreDataDTO create(GenreCreateRequestDTO createRequestDTO) {
		// TODO Auto-generated method stub
		Boolean existNname = this.genreRepository.existsByName(createRequestDTO.getName());
		if(existNname) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + createRequestDTO.getName() + " ya existe.");
		}
		GenreEntity genreEntity = this.genreMapper.mapGenreCreateRequestToGenreEntity(createRequestDTO);
		genreEntity.setCreatedAt(LocalDateTime.now());
		genreEntity.setUpdatedAt(LocalDateTime.now());
		
		GenreDataDTO genreCreated = this.genreMapper.mapGenreEntityToGenreDataDTO(this.genreRepository.save(genreEntity));			
		return genreCreated;
	}

	@Override
	public GenreDataDTO update(Integer id, GenreUpdateRequestDTO updateRequestDTO) {
		// TODO Auto-generated method stub
		GenreEntity genreDataCurrent = this.genreRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Género", "id", id));
		Boolean existsUsername = this.genreRepository.existsByName(updateRequestDTO.getName());
		Boolean diferentUsernameCurrent = (!updateRequestDTO.getName().equalsIgnoreCase(genreDataCurrent.getName()));
		if(existsUsername && diferentUsernameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + updateRequestDTO.getName() + " ya existe.");
		}
		genreDataCurrent.setName(updateRequestDTO.getName());
		genreDataCurrent.setUpdatedAt(LocalDateTime.now());
		
		GenreDataDTO genreUpdated = this.genreMapper.mapGenreEntityToGenreDataDTO(this.genreRepository.save(genreDataCurrent));	
		return genreUpdated;
	}

	@Override
	public GenreDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		GenreEntity genreEntity = this.genreRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Género", "id", id));
		this.genreRepository.delete(genreEntity);
		GenreDataDTO genreDeleted = this.genreMapper.mapGenreEntityToGenreDataDTO(genreEntity);
		return genreDeleted;
	}

}