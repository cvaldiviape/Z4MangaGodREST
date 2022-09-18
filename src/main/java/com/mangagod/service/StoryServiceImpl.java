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

import com.mangagod.dto.pagination.StoryAllPageableDataDTO;
import com.mangagod.dto.request.MangakaJobRequestDTO;
import com.mangagod.dto.request.StoryRequestDTO;
import com.mangagod.dto.response.MangakasJobsResponseDTO;
import com.mangagod.dto.response.StoryResponseDTO;
import com.mangagod.entity.CategoryEntity;
import com.mangagod.entity.CountryEntity;
import com.mangagod.entity.DemographyEntity;
import com.mangagod.entity.GenreEntity;
import com.mangagod.entity.JobEntity;
import com.mangagod.entity.MangakaEntity;
import com.mangagod.entity.StoryEntity;
import com.mangagod.entity.StoryMangakaEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.StoryMapper;
import com.mangagod.repository.CategoryRepository;
import com.mangagod.repository.CountryRepository;
import com.mangagod.repository.DemographyRepository;
import com.mangagod.repository.GenreRepository;
import com.mangagod.repository.JobRepository;
import com.mangagod.repository.MangakaRepository;
import com.mangagod.repository.StoryMangakaRepository;
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
	private MangakaRepository mangakaRepository;
	@Autowired
	private StoryMangakaRepository storyMangakaRepository;
	@Autowired
	private JobRepository jobRepository;
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
		List<StoryResponseDTO> storiesDTO = storiesEntity.stream().map((x) -> this.storyMapper.mapEntityToResponseDTO(x)).collect(Collectors.toList());	
		
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
	public StoryResponseDTO getById(Integer id) {
		// TODO Auto-generated method stub
		StoryEntity entity = this.getStoryById(id);
		StoryResponseDTO dataDTO = this.storyMapper.mapEntityToResponseDTO(entity);
		
		Set<MangakasJobsResponseDTO> listMangakasJobsDataDTO = this.getListMangakasJobsDataDTO(entity.getStoriesMangakas());
		dataDTO.setMangakasJobs(listMangakasJobsDataDTO);
		
		return dataDTO;
	}

	@Override
	public StoryResponseDTO create(StoryRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		StoryEntity storyEntity = this.storyMapper.mapRequestToEntity(requestDTO);
		
		// seteando "country", "demography", "category" y "genres".
		CountryEntity countryEntity = this.getCountryById(requestDTO.getCountryId());
		DemographyEntity demographyEntity = this.getDemographyById(requestDTO.getDemographyId());
		CategoryEntity categoryEntity = this.getCategoryById(requestDTO.getCategoryId());
		Set<GenreEntity> genres = this.getListGenresbYIds(requestDTO.getGenreIds());
		storyEntity.setCountry(countryEntity);
		storyEntity.setDemography(demographyEntity);
		storyEntity.setCategory(categoryEntity);
		storyEntity.setGenres(genres);
		
		// verificando que el campo "title" sea unico.
		this.verifyTitleUnique(requestDTO.getTitle());
					
		// creando "stories_mangakas".
		Set<StoryMangakaEntity> storyMangakaEntities = new HashSet<>();
		for (MangakaJobRequestDTO mj : requestDTO.getMangakaJobIds()) {
			MangakaEntity mangakaEntity = this.getMangakaById(mj.getMangakaId());
			JobEntity jobEntity = this.getJobById(mj.getJobId());
			StoryMangakaEntity storyMangakaEntity = new StoryMangakaEntity();
			storyMangakaEntity.setStory(storyEntity);
			storyMangakaEntity.setMangaka(mangakaEntity);
			storyMangakaEntity.setJob(jobEntity);
			storyMangakaEntities.add(storyMangakaEntity);
		}
		
		// seteando "stories_mangakas"
		storyEntity.setStoriesMangakas(storyMangakaEntities);
		
		// creando "story".
		StoryEntity storyCreated = this.storyRepository.save(storyEntity);
		
		// seteando response "storyCreated"	
		StoryResponseDTO dataCreated = this.storyMapper.mapEntityToResponseDTO(storyCreated);
		
		// seteando responte lista "mangakas_jobs"
		Set<MangakasJobsResponseDTO> mangakasJobsDataDTO = new HashSet<>();
		for (StoryMangakaEntity smEntity : storyMangakaEntities) {
			MangakasJobsResponseDTO mjDataDTO = new MangakasJobsResponseDTO();
			mjDataDTO.setMangakaId(smEntity.getMangaka().getId());
			mjDataDTO.setNameMangaka(smEntity.getMangaka().getName());
			mjDataDTO.setJobId(smEntity.getJob().getId());
			mjDataDTO.setNameJob(smEntity.getJob().getName());
			mangakasJobsDataDTO.add(mjDataDTO);
		}
		dataCreated.setMangakasJobs(mangakasJobsDataDTO);
		
		return 	dataCreated;
	}

	@Override
	public StoryResponseDTO update(Integer id, StoryRequestDTO requestDTO) {	
		// TODO Auto-generated method stub
		
		// eliminando relaciones anterioes con "mangakas"
		this.storyMangakaRepository.deleteByStoryId(id);
				
		// obteniendo "story" en base a su "Id"
		StoryEntity dataCurrent = this.getStoryById(id);
		
		// seteando "country", "demography", "category" y "genres".
		CountryEntity countryEntity = this.getCountryById(requestDTO.getCountryId());
		DemographyEntity demographyEntity = this.getDemographyById(requestDTO.getDemographyId());
		CategoryEntity categoryEntity = this.getCategoryById(requestDTO.getCategoryId());
		Set<GenreEntity> genres = this.getListGenresbYIds(requestDTO.getGenreIds());
		dataCurrent.setCountry(countryEntity);
		dataCurrent.setDemography(demographyEntity);
		dataCurrent.setCategory(categoryEntity);
		dataCurrent.setGenres(genres);
		
		// verificando que el campo "title" sea unico.
		this.verifyTitleUnique(requestDTO.getTitle(), dataCurrent.getTitle());
		
		// seteando valores actualizados
		dataCurrent.setTitle(requestDTO.getTitle());
		dataCurrent.setYear(requestDTO.getYear());
		dataCurrent.setSynopsis(requestDTO.getSynopsis());
		dataCurrent.setState(requestDTO.getState());
		dataCurrent.setUrlImage(requestDTO.getUrlImage());
		dataCurrent.setAdaptationAnime(requestDTO.getAdaptationAnime());
		dataCurrent.setPrice(requestDTO.getPrice());
		
		// actualizando "stories_mangakas".
		Set<StoryMangakaEntity> storyMangakaEntities = new HashSet<>();
		for (MangakaJobRequestDTO mj : requestDTO.getMangakaJobIds()) {
			
			MangakaEntity mangakaEntity = this.getMangakaById(mj.getMangakaId());
			JobEntity jobEntity = this.getJobById(mj.getJobId());
			
			StoryMangakaEntity storyMangakaEntity = new StoryMangakaEntity();
			storyMangakaEntity.setStory(dataCurrent);
			storyMangakaEntity.setMangaka(mangakaEntity);
			storyMangakaEntity.setJob(jobEntity);
			storyMangakaEntities.add(storyMangakaEntity);
		}
		
		// seteando "stories_mangakas"
		dataCurrent.setStoriesMangakas(storyMangakaEntities);
			
		// actualizando "story".			
		StoryEntity storyUpdated = this.storyRepository.save(dataCurrent);	
		
		// response "storyUpdate"	
		StoryResponseDTO dataUpdated = this.storyMapper.mapEntityToResponseDTO(storyUpdated);
		
		// seteando responte lista "mangakas_jobs".
		Set<MangakasJobsResponseDTO> listMangakasJobsDataDTO = new HashSet<>();
		for (StoryMangakaEntity smEntity : storyMangakaEntities) {
			MangakasJobsResponseDTO mjDataDTO = new MangakasJobsResponseDTO();
			mjDataDTO.setMangakaId(smEntity.getMangaka().getId());
			mjDataDTO.setNameMangaka(smEntity.getMangaka().getName());
			mjDataDTO.setJobId(smEntity.getJob().getId());
			mjDataDTO.setNameJob(smEntity.getJob().getName());
			listMangakasJobsDataDTO.add(mjDataDTO);
		}
		dataUpdated.setMangakasJobs(listMangakasJobsDataDTO);
		
		return dataUpdated;
	}
	
	@Override
	public StoryResponseDTO delete(Integer id) {
		// TODO Auto-generated method stub
		
		// eliminando relaciones con "stories_mangakas"
		this.storyMangakaRepository.deleteByStoryId(id);
		// obteniendo "story" para eliminarlo
		StoryEntity entity = this.getStoryById(id);
		// eliminando "story"
		this.storyRepository.delete(entity);
		// response
		return this.storyMapper.mapEntityToResponseDTO(entity);
	}

	// ----------------------------------------------------------- utils ----------------------------------------------------------- ((
	public CountryEntity getCountryById(Integer id) {
		return this.countryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pais", "id", id));
	}
	
	public DemographyEntity getDemographyById(Integer id) {
		return this.demographyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Demografía", "id", id));
	}
	
	public CategoryEntity getCategoryById(Integer id) {
		return this.categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id));
	}
	
	public MangakaEntity getMangakaById(Integer id) {
		return this.mangakaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Mangaka", "id", id));
	}
	
	public JobEntity getJobById(Integer id) {
		return this.jobRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ocupación", "id", id));
	}
	
	public StoryEntity getStoryById(Integer id) {
		return this.storyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Historieta", "id", id));
	}
	
	public Set<GenreEntity> getListGenresbYIds(Set<Integer> genresIds){
		Set<GenreEntity> genres = new HashSet<>();		
		for (Integer id : genresIds) {
			GenreEntity genreEntity = this.genreRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Género", "id", id));
			genres.add(genreEntity);
		}
		return genres;
	} 
	
	public void verifyTitleUnique(String title) {
		Boolean existTitle = this.storyRepository.existsByTitle(title);
		if(existTitle) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El título " + title + " ya existe.");
		}
	}
	
	public void verifyTitleUnique(String title, String titleCurrent) {
		Boolean existTitle = this.storyRepository.existsByTitle(title);
		Boolean diferentTitleCurrent = (!title.equalsIgnoreCase(titleCurrent));
		if(existTitle && diferentTitleCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El título " + title + " ya existe.");
		}
	}
	
	public Set<MangakasJobsResponseDTO> getListMangakasJobsDataDTO(Set<StoryMangakaEntity> storiesMangakas){
		Set<MangakasJobsResponseDTO> mangakasJobsDataDTOs = new HashSet<>();
		
		for (StoryMangakaEntity smEntity : storiesMangakas) {
			MangakasJobsResponseDTO  mjDataDTO = new MangakasJobsResponseDTO();
			mjDataDTO.setMangakaId(smEntity.getMangaka().getId());
			mjDataDTO.setNameMangaka(smEntity.getMangaka().getName());
			mjDataDTO.setJobId(smEntity.getJob().getId());
			mjDataDTO.setNameJob(smEntity.getJob().getName());
			mangakasJobsDataDTOs.add(mjDataDTO);
		}
		return mangakasJobsDataDTOs;
	}
	
}