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
import com.mangagod.dto.data.JobDataDTO;
import com.mangagod.dto.pagination.JobAllPageableDataDTO;
import com.mangagod.dto.request.JobRequestDTO;
import com.mangagod.entity.JobEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.JobMapper;
import com.mangagod.repository.JobRepository;

@Service
@Transactional
public class JobServiceImpl implements JobService {
	
	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private JobMapper jobMapper;
	
	// ----------------------------------------------------------- services ----------------------------------------------------------- //
		

	@Override
	public JobAllPageableDataDTO getAll(Integer numberPage, Integer sizePage, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending();
	
		// agregando paginación
		Pageable pageable = PageRequest.of(numberPage, sizePage, sort);
		Page<JobEntity> jobsPageable = this.jobRepository.findAll(pageable);	
		List<JobEntity> jobsEntity = jobsPageable.getContent();
		List<JobDataDTO> jobsDTO = jobsEntity.stream().map((x) -> this.jobMapper.mapEntityToDataDTO(x)).collect(Collectors.toList());	
		
		JobAllPageableDataDTO pageableDataDTO = new JobAllPageableDataDTO();
		pageableDataDTO.setGenres(jobsDTO);
		pageableDataDTO.setNumberPage(jobsPageable.getNumber());
		pageableDataDTO.setSizePage(jobsPageable.getSize());
		pageableDataDTO.setTotalElements(jobsPageable.getTotalElements());
		pageableDataDTO.setTotalPages(jobsPageable.getTotalPages());
		pageableDataDTO.setIsLastPage(jobsPageable.isLast());
		
		return pageableDataDTO;
	}

	@Override
	public JobDataDTO getById(Integer id) {
		// TODO Auto-generated method stub
		JobEntity entity = this.jobRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ocupación", "id", id));
		JobDataDTO dataDTO = this.jobMapper.mapEntityToDataDTO(entity);
		return dataDTO;
	}

	@Override
	public JobDataDTO create(JobRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		Boolean existNname = this.jobRepository.existsByName(requestDTO.getName());
		if(existNname) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		JobEntity entity = this.jobMapper.mapRequestToEntity(requestDTO);
		
		JobDataDTO dataCreated = this.jobMapper.mapEntityToDataDTO(this.jobRepository.save(entity));			
		return dataCreated;
	}

	@Override
	public JobDataDTO update(Integer id, JobRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		JobEntity dataCurrent = this.jobRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ocupación", "id", id));
		Boolean existsNname = this.jobRepository.existsByName(requestDTO.getName());
		Boolean diferentNameCurrent = (!requestDTO.getName().equalsIgnoreCase(dataCurrent.getName()));
		if(existsNname && diferentNameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		dataCurrent.setName(requestDTO.getName());
		
		JobDataDTO dataUpdated = this.jobMapper.mapEntityToDataDTO(this.jobRepository.save(dataCurrent));	
		return dataUpdated;
	}

	@Override
	public JobDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		JobEntity entity = this.jobRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ocupación", "id", id));
		this.jobRepository.delete(entity);
		JobDataDTO dataDeleted = this.jobMapper.mapEntityToDataDTO(entity);
		return dataDeleted;
	}

}