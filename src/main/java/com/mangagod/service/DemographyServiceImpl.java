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

import com.mangagod.dto.data.DemographyDataDTO;
import com.mangagod.dto.pagination.DemographyAllPageableDataDTO;
import com.mangagod.dto.request.DemographyRequestDTO;
import com.mangagod.entity.DemographyEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.DemographyMapper;
import com.mangagod.repository.DemographyRepository;

@Service
@Transactional
public class DemographyServiceImpl implements DemographyService{

	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private DemographyRepository demographyRepository;
	@Autowired
	private DemographyMapper demographyMapper;
	
	// ----------------------------------------------------------- services ----------------------------------------------------------- //
	@Override
	public DemographyAllPageableDataDTO getAll(Integer numberPage, Integer sizePage, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending();
	
		// agregando paginación
		Pageable pageable = PageRequest.of(numberPage, sizePage, sort);
		Page<DemographyEntity> demographiesPageable = this.demographyRepository.findAll(pageable);	
		List<DemographyEntity> demographiesEntity = demographiesPageable.getContent();
		List<DemographyDataDTO> demographiesDTO = demographiesEntity.stream().map(demography -> this.demographyMapper.mapEntityToDataDTO(demography)).collect(Collectors.toList());	
		
		DemographyAllPageableDataDTO demographyAllPageableDataDTO = new DemographyAllPageableDataDTO();
		demographyAllPageableDataDTO.setDemogrhapies(demographiesDTO);
		demographyAllPageableDataDTO.setNumberPage(demographiesPageable.getNumber());
		demographyAllPageableDataDTO.setSizePage(demographiesPageable.getSize());
		demographyAllPageableDataDTO.setTotalElements(demographiesPageable.getTotalElements());
		demographyAllPageableDataDTO.setTotalPages(demographiesPageable.getTotalPages());
		demographyAllPageableDataDTO.setIsLastPage(demographiesPageable.isLast());
		
		return demographyAllPageableDataDTO;
	}

	@Override
	public DemographyDataDTO getById(Integer id) {
		// TODO Auto-generated method stub
		DemographyEntity entity = this.demographyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Demogragía", "id", id));
		DemographyDataDTO dataCreated = this.demographyMapper.mapEntityToDataDTO(entity);
		return dataCreated;
	}

	@Override
	public DemographyDataDTO create(DemographyRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		Boolean existNname = this.demographyRepository.existsByName(requestDTO.getName());
		if(existNname) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		DemographyEntity countryEntity = this.demographyMapper.mapRequestToEntity(requestDTO);
		countryEntity.setCreatedAt(LocalDateTime.now());
		countryEntity.setUpdatedAt(LocalDateTime.now());
		
		DemographyDataDTO dataDTO = this.demographyMapper.mapEntityToDataDTO(this.demographyRepository.save(countryEntity));			
		return dataDTO;
	}

	@Override
	public DemographyDataDTO update(Integer id, DemographyRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		DemographyEntity dataCurrent = this.demographyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pais", "id", id));
		Boolean existsName = this.demographyRepository.existsByName(requestDTO.getName());
		Boolean diferentUsernameCurrent = (!requestDTO.getName().equalsIgnoreCase(dataCurrent.getName()));
		if(existsName && diferentUsernameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		dataCurrent.setName(requestDTO.getName().trim());
		dataCurrent.setUpdatedAt(LocalDateTime.now());
		
		DemographyDataDTO dataUpdated = this.demographyMapper.mapEntityToDataDTO(this.demographyRepository.save(dataCurrent));	
		return dataUpdated;
	}

	@Override
	public DemographyDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		DemographyEntity entity = this.demographyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pais", "id", id));
		this.demographyRepository.delete(entity);
		DemographyDataDTO dataDeleted = this.demographyMapper.mapEntityToDataDTO(entity);
		return dataDeleted;
	}

}