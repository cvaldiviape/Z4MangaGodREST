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

import com.mangagod.dto.pagination.MangakaAllPageableDataDTO;
import com.mangagod.dto.request.MangakaRequestDTO;
import com.mangagod.dto.request.StoryJobRequestDTO;
import com.mangagod.dto.response.MangakaResponseDTO;
import com.mangagod.dto.response.StoriesJobsResponseDTO;
import com.mangagod.entity.JobEntity;
import com.mangagod.entity.MangakaEntity;
import com.mangagod.entity.StoryEntity;
import com.mangagod.entity.StoryMangakaEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.MangakaMapper;
import com.mangagod.repository.JobRepository;
import com.mangagod.repository.MangakaRepository;
import com.mangagod.repository.StoryMangakaRepository;
import com.mangagod.repository.StoryRepository;
import com.mangagod.util.enums.Sex;

@Service
@Transactional
public class MangakaServiceImpl implements MangakaService {

	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private MangakaRepository mangakaRepository;
	@Autowired
	private StoryRepository storyRepository;
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private StoryMangakaRepository storyMangakaRepository;
	@Autowired
	private MangakaMapper mangakaMapper;
	
	// ----------------------------------------------------------- services ----------------------------------------------------------- //
	@Override
	public MangakaAllPageableDataDTO getAll(Integer numberPage, Integer sizePage, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending();
	
		// agregando paginación
		Pageable pageable = PageRequest.of(numberPage, sizePage, sort);
		Page<MangakaEntity> mangakasPageable = this.mangakaRepository.findAll(pageable);	
		List<MangakaEntity> mangakasEntity = mangakasPageable.getContent();
		List<MangakaResponseDTO> mangakassDTO = mangakasEntity.stream().map((x) -> this.mangakaMapper.mapEntityToResponseDTO(x)).collect(Collectors.toList());	
		
		MangakaAllPageableDataDTO pageableDataDTO = new MangakaAllPageableDataDTO();
		pageableDataDTO.setMangakas(mangakassDTO);
		pageableDataDTO.setNumberPage(mangakasPageable.getNumber());
		pageableDataDTO.setSizePage(mangakasPageable.getSize());
		pageableDataDTO.setTotalElements(mangakasPageable.getTotalElements());
		pageableDataDTO.setTotalPages(mangakasPageable.getTotalPages());
		pageableDataDTO.setIsLastPage(mangakasPageable.isLast());
		
		return pageableDataDTO;
	}

	@Override
	public MangakaResponseDTO getById(Integer id) {
		// TODO Auto-generated method stub
		MangakaEntity entity = this.getMangakaById(id);
		MangakaResponseDTO dataDTO = this.mangakaMapper.mapEntityToResponseDTO(entity);
		
		Set<StoriesJobsResponseDTO> listStoriesJobsDataDTO = this.getListStoriesJobsDataDTO(entity.getStoriesMangakas());
		dataDTO.setStoriesJobs(listStoriesJobsDataDTO);
		
		return dataDTO;
	}

	@Override
	public MangakaResponseDTO create(MangakaRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		MangakaEntity mangakaEntity = this.mangakaMapper.mapRequestToEntity(requestDTO);
		
		// verificando que el campo "title" sea unico.
		this.verifyNameUnique(requestDTO.getName());
		
		// creando "stories_mangakas".
		Set<StoryMangakaEntity> storyMangakaEntities = new HashSet<>();
		for (StoryJobRequestDTO sj : requestDTO.getStoryJobIds()) {
			
			StoryEntity  storyEntity = this.getStoryById(sj.getStoryId());
			JobEntity jobEntity = this.getJobById(sj.getJobId());
			
			StoryMangakaEntity storyMangakaEntity = new StoryMangakaEntity();
			storyMangakaEntity.setMangaka(mangakaEntity);
			storyMangakaEntity.setStory(storyEntity);
			storyMangakaEntity.setJob(jobEntity);
			storyMangakaEntities.add(storyMangakaEntity);
		}
		
		// seteando "stories_mangakas"
		mangakaEntity.setStoriesMangakas(storyMangakaEntities);
		
		// creando "mangaka".
		MangakaEntity mangakaCreated = this.mangakaRepository.save(mangakaEntity);
		
		// seteando response "storyCreated"	
		MangakaResponseDTO dataCreated = this.mangakaMapper.mapEntityToResponseDTO(mangakaCreated);
		
		// seteando responte lista "stories_jobs"
		Set<StoriesJobsResponseDTO> listStoriesJobsDataDTO = new HashSet<>();
		for (StoryMangakaEntity smEntity : storyMangakaEntities) {
			StoriesJobsResponseDTO sjDataDTO = new StoriesJobsResponseDTO();
			sjDataDTO.setStoryId(smEntity.getStory().getId());
			sjDataDTO.setNameStory(smEntity.getStory().getTitle());
			sjDataDTO.setJobId(smEntity.getJob().getId());
			sjDataDTO.setNameJob(smEntity.getJob().getName());
			listStoriesJobsDataDTO.add(sjDataDTO);
		}
		dataCreated.setStoriesJobs(listStoriesJobsDataDTO);
		
		return 	dataCreated;
	}

	@Override
	public MangakaResponseDTO update(Integer id, MangakaRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		
		// eliminando relaciones anterioes con "stories"
		this.storyMangakaRepository.deleteByMangakaId(id);
		
		// obteniendo "mangaka" en base a su "Id"
		MangakaEntity dataCurrent = this.getMangakaById(id);
		
		// verificando que el campo "name" sea unico.
		this.verifyNameUnique(requestDTO.getName(), dataCurrent.getName());
		
		// seteando valores actualizados
		dataCurrent.setName(requestDTO.getName());
		dataCurrent.setSex(Sex.valueOf(requestDTO.getSex()));
		dataCurrent.setBirthDate(requestDTO.getBirthDate());
		
		// creando "stories_mangakas".
		Set<StoryMangakaEntity> storyMangakaEntities = new HashSet<>();
		for (StoryJobRequestDTO sj : requestDTO.getStoryJobIds()) {
			
			StoryEntity  storyEntity = this.getStoryById(sj.getStoryId());
			JobEntity jobEntity = this.getJobById(sj.getJobId());
			
			StoryMangakaEntity storyMangakaEntity = new StoryMangakaEntity();
			storyMangakaEntity.setMangaka(dataCurrent);
			storyMangakaEntity.setStory(storyEntity);
			storyMangakaEntity.setJob(jobEntity);
			storyMangakaEntities.add(storyMangakaEntity);
		}
		
		// seteando "stories_mangakas"
		dataCurrent.setStoriesMangakas(storyMangakaEntities);
		
		// creando "mangaka".
		MangakaEntity mangakaUpdated = this.mangakaRepository.save(dataCurrent);
		
		// seteando response "storyCreated"	
		MangakaResponseDTO dataUpdated = this.mangakaMapper.mapEntityToResponseDTO(mangakaUpdated);
		
		// seteando responte lista "stories_jobs"
		Set<StoriesJobsResponseDTO> listStoriesJobsDataDTO = new HashSet<>();
		for (StoryMangakaEntity smEntity : storyMangakaEntities) {
			StoriesJobsResponseDTO sjDataDTO = new StoriesJobsResponseDTO();
			sjDataDTO.setStoryId(smEntity.getStory().getId());
			sjDataDTO.setNameStory(smEntity.getStory().getTitle());
			sjDataDTO.setJobId(smEntity.getJob().getId());
			sjDataDTO.setNameJob(smEntity.getJob().getName());
			listStoriesJobsDataDTO.add(sjDataDTO);
		}
		dataUpdated.setStoriesJobs(listStoriesJobsDataDTO);
		
		return 	dataUpdated;
	}

	@Override
	public MangakaResponseDTO delete(Integer id) {
		// TODO Auto-generated method stub
		
		// eliminando relaciones con "stories_mangakas"
		this.storyMangakaRepository.deleteByMangakaId(id);
		// obteniendo "story" para eliminarlo
		MangakaEntity entity = this.getMangakaById(id);
		// eliminando mangaka.
		this.mangakaRepository.delete(entity);
		// response
		return this.mangakaMapper.mapEntityToResponseDTO(entity);	
	}

	// ----------------------------------------------------------- utils ----------------------------------------------------------- //
	public StoryEntity getStoryById(Integer id) {
		return this.storyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Historieta", "id", id));
	}
	
	public JobEntity getJobById(Integer id) {
		return this.jobRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ocupación", "id", id));
	}
	
	public MangakaEntity getMangakaById(Integer id) {
		return this.mangakaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Mangaka", "id", id));
	}
	
	public void verifyNameUnique(String name) {
		Boolean existNname = this.mangakaRepository.existsByName(name);
		if(existNname) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + name + " ya existe.");
		}
	}
	
	public void verifyNameUnique(String name, String nameCurrent) {
		Boolean existNname = this.mangakaRepository.existsByName(name);
		Boolean diferentNameCurrent = (!name.equalsIgnoreCase(nameCurrent));
		if(existNname && diferentNameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + name + " ya existe.");
		}
	}

	public Set<StoriesJobsResponseDTO> getListStoriesJobsDataDTO(Set<StoryMangakaEntity> storiesMangakas){
		Set<StoriesJobsResponseDTO> storiesJobsDataDTOs = new HashSet<>();
		
		for (StoryMangakaEntity smEntity : storiesMangakas) {
			StoriesJobsResponseDTO  mjDataDTO = new StoriesJobsResponseDTO();
			mjDataDTO.setStoryId(smEntity.getStory().getId());
			mjDataDTO.setNameStory(smEntity.getStory().getTitle());
			mjDataDTO.setJobId(smEntity.getJob().getId());
			mjDataDTO.setNameJob(smEntity.getJob().getName());
			storiesJobsDataDTOs.add(mjDataDTO);
		}
		return storiesJobsDataDTOs;
	}
}