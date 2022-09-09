package com.mangagod.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mangagod.dto.data.StoryDataDTO;
import com.mangagod.dto.pagination.StoryAllPageableDataDTO;
import com.mangagod.dto.request.StoryRequestDTO;
import com.mangagod.entity.CategoryEntity;
import com.mangagod.entity.CountryEntity;
import com.mangagod.entity.DemographyEntity;
import com.mangagod.entity.GenreEntity;
import com.mangagod.entity.StoryEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.StoryMapper;
import com.mangagod.repository.CategoryRepository;
import com.mangagod.repository.CountryRepository;
import com.mangagod.repository.DemographyRepository;
import com.mangagod.repository.GenreRepository;
import com.mangagod.repository.StoryRepository;

@Service
@Transactional
public class StoryServiceImpl implements StoryService {

	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private StoryRepository storyRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private DemographyRepository demographyRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private StoryMapper storyMapper;
	
	@Override
	public StoryAllPageableDataDTO getAll(Integer numberPage, Integer sizePage, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending();
	
		// agregando paginación
		Pageable pageable = PageRequest.of(numberPage, sizePage, sort);
		Page<StoryEntity> storiesPageable = this.storyRepository.findAll(pageable);
		List<StoryEntity> storiesEntity = storiesPageable.getContent();
		List<StoryDataDTO> storiesDTO = storiesEntity.stream().map((x) -> this.storyMapper.mapEntityToDataDTO(x)).collect(Collectors.toList());	
		
		StoryAllPageableDataDTO pageableDataDTO = new StoryAllPageableDataDTO();
		pageableDataDTO.setStories(storiesDTO);
		pageableDataDTO.setNumberPage(storiesPageable.getNumber());
		pageableDataDTO.setSizePage(storiesPageable.getSize());
		pageableDataDTO.setTotalElements(storiesPageable.getTotalElements());
		pageableDataDTO.setTotalPages(storiesPageable.getTotalPages());
		pageableDataDTO.setIsLastPage(storiesPageable.isLast());
		
		return pageableDataDTO;
	}

	@Override
	public StoryDataDTO getById(Integer id) {
		// TODO Auto-generated method stub
		StoryEntity entity = this.storyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Historieta", "id", id));
		StoryDataDTO dataDTO = this.storyMapper.mapEntityToDataDTO(entity);
		return dataDTO;
	}

	@Override
	public StoryDataDTO create(StoryRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		CountryEntity countryEntity = this.countryRepository.findById(requestDTO.getCountryId())
				.orElseThrow(() -> new ResourceNotFoundException("Pais", "id", requestDTO.getCountryId()));
		DemographyEntity demographyEntity = this.demographyRepository.findById(requestDTO.getDemographyId())
				.orElseThrow(() -> new ResourceNotFoundException("Demografía", "id", requestDTO.getDemographyId()));
		CategoryEntity categoryEntity = this.categoryRepository.findById(requestDTO.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Categoría", "id", requestDTO.getCategoryId()));
		
		StoryEntity entity = this.storyMapper.mapRequestToEntity(requestDTO);
		entity.setCountry(countryEntity);
		entity.setDemography(demographyEntity);
		entity.setCategory(categoryEntity);
		
		Boolean existTitle = this.storyRepository.existsByTitle(requestDTO.getTitle());
		if(existTitle) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El título " + requestDTO.getTitle() + " ya existe.");
		}
		
		Set<GenreEntity> genres = new HashSet<>();		
		for (Integer genreId : requestDTO.getGenreIds()) {
			GenreEntity genreEntity = this.genreRepository.findById(genreId)
					.orElseThrow(() -> new ResourceNotFoundException("Género", "id", genreId));
			genres.add(genreEntity);
		}
		entity.setGenres(genres);
		
		StoryDataDTO dataCreated = this.storyMapper.mapEntityToDataDTO(this.storyRepository.save(entity));			
		return dataCreated;
	}

	@Override
	public StoryDataDTO update(Integer id, StoryRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		StoryEntity dataCurrent = this.storyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Historieta", "id", id));
		
		Boolean existTitle = this.storyRepository.existsByTitle(requestDTO.getTitle());
		Boolean diferentTitleCurrent = (!requestDTO.getTitle().equalsIgnoreCase(dataCurrent.getTitle()));
		if(existTitle && diferentTitleCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El título " + requestDTO.getTitle() + " ya existe.");
		}
		
		CountryEntity countryEntity = this.countryRepository.findById(requestDTO.getCountryId())
				.orElseThrow(() -> new ResourceNotFoundException("Pais", "id", requestDTO.getCountryId()));
		DemographyEntity demographyEntity = this.demographyRepository.findById(requestDTO.getDemographyId())
				.orElseThrow(() -> new ResourceNotFoundException("Demografía", "id", requestDTO.getDemographyId()));
		CategoryEntity categoryEntity = this.categoryRepository.findById(requestDTO.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Categoría", "id", requestDTO.getCategoryId()));
		
		Set<GenreEntity> genres = new HashSet<>();		
		for (Integer genreId : requestDTO.getGenreIds()) {
			GenreEntity genreEntity = this.genreRepository.findById(genreId)
					.orElseThrow(() -> new ResourceNotFoundException("Género", "id", genreId));
			genres.add(genreEntity);
		}
		
		dataCurrent.setTitle(requestDTO.getTitle());
		dataCurrent.setYear(requestDTO.getYear());
		dataCurrent.setSynopsis(requestDTO.getSynopsis());
		dataCurrent.setState(requestDTO.getState());
		dataCurrent.setUrlImage(requestDTO.getUrlImage());
		dataCurrent.setAdaptationAnime(requestDTO.getAdaptationAnime());
		dataCurrent.setPrice(requestDTO.getPrice());
		dataCurrent.setCountry(countryEntity);
		dataCurrent.setDemography(demographyEntity);
		dataCurrent.setCategory(categoryEntity);
		dataCurrent.setGenres(genres);
		
		StoryDataDTO dataCreated = this.storyMapper.mapEntityToDataDTO(this.storyRepository.save(dataCurrent));			
		return dataCreated;
	}

	@Override
	public StoryDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		StoryEntity entity = this.storyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Historieta", "id", id));
		this.storyRepository.delete(entity);
		StoryDataDTO dataDeleted = this.storyMapper.mapEntityToDataDTO(entity);
		return dataDeleted;
	}

}