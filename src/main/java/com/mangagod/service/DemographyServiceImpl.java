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

import com.mangagod.dto.data.DemographyAllPageableDataDTO;
import com.mangagod.dto.data.DemographyDataDTO;
import com.mangagod.dto.request.DemographyCreateRequestDTO;
import com.mangagod.dto.request.DemographyUpdateRequestDTO;
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
		List<DemographyDataDTO> demographiesDTO = demographiesEntity.stream().map(demography -> this.demographyMapper.mapDemographyEntityToDemographyDataDTO(demography)).collect(Collectors.toList());	
		
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
		DemographyEntity demographyEntity = this.demographyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Demogragía", "id", id));
		DemographyDataDTO demographyCreated = this.demographyMapper.mapDemographyEntityToDemographyDataDTO(demographyEntity);
		return demographyCreated;
	}

	@Override
	public DemographyDataDTO create(DemographyCreateRequestDTO createRequestDTO) {
		// TODO Auto-generated method stub
		Boolean existNname = this.demographyRepository.existsByName(createRequestDTO.getName());
		if(existNname) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + createRequestDTO.getName() + " ya existe.");
		}
		DemographyEntity countryEntity = this.demographyMapper.mapDemographyCreateRequestToDemographyEntity(createRequestDTO);
		countryEntity.setCreatedAt(LocalDateTime.now());
		countryEntity.setUpdatedAt(LocalDateTime.now());
		
		DemographyDataDTO demographyDataDTO = this.demographyMapper.mapDemographyEntityToDemographyDataDTO(this.demographyRepository.save(countryEntity));			
		return demographyDataDTO;
	}

	@Override
	public DemographyDataDTO update(Integer id, DemographyUpdateRequestDTO updateRequestDTO) {
		// TODO Auto-generated method stub
		DemographyEntity demographyDataCurrent = this.demographyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pais", "id", id));
		Boolean existsName = this.demographyRepository.existsByName(updateRequestDTO.getName());
		Boolean diferentUsernameCurrent = (!updateRequestDTO.getName().equalsIgnoreCase(demographyDataCurrent.getName()));
		if(existsName && diferentUsernameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + updateRequestDTO.getName() + " ya existe.");
		}
		demographyDataCurrent.setName(updateRequestDTO.getName().trim());
		demographyDataCurrent.setUpdatedAt(LocalDateTime.now());
		
		DemographyDataDTO demographyUpdated = this.demographyMapper.mapDemographyEntityToDemographyDataDTO(this.demographyRepository.save(demographyDataCurrent));	
		return demographyUpdated;
	}

	@Override
	public DemographyDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		DemographyEntity demographyEntity = this.demographyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pais", "id", id));
		this.demographyRepository.delete(demographyEntity);
		DemographyDataDTO demographyDeleted = this.demographyMapper.mapDemographyEntityToDemographyDataDTO(demographyEntity);
		return demographyDeleted;
	}

}