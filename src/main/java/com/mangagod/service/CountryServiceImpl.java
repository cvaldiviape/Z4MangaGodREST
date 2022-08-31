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

import com.mangagod.dto.data.CountryAllPageableDataDTO;
import com.mangagod.dto.data.CountryDataDTO;
import com.mangagod.dto.request.CountryRequestDTO;
import com.mangagod.entity.CountryEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.CountryMapper;
import com.mangagod.repository.CountryRepository;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {
	
	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private CountryMapper countryMapper;
	
	// ----------------------------------------------------------- services ----------------------------------------------------------- //
	@Override
	public CountryAllPageableDataDTO getAll(Integer numberPage, Integer sizePage, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending();
	
		// agregando paginación
		Pageable pageable = PageRequest.of(numberPage, sizePage, sort);
		Page<CountryEntity> countriesPageable = this.countryRepository.findAll(pageable);	
		List<CountryEntity> countriesEntity = countriesPageable.getContent();
		List<CountryDataDTO> countriesDTO = countriesEntity.stream().map(country -> this.countryMapper.mapEntityToDataDTO(country)).collect(Collectors.toList());	
		
		CountryAllPageableDataDTO countryAllPageableDataDTO = new CountryAllPageableDataDTO();
		countryAllPageableDataDTO.setCountries(countriesDTO);
		countryAllPageableDataDTO.setNumberPage(countriesPageable.getNumber());
		countryAllPageableDataDTO.setSizePage(countriesPageable.getSize());
		countryAllPageableDataDTO.setTotalElements(countriesPageable.getTotalElements());
		countryAllPageableDataDTO.setTotalPages(countriesPageable.getTotalPages());
		countryAllPageableDataDTO.setIsLastPage(countriesPageable.isLast());
		
		return countryAllPageableDataDTO;
	}
	
	@Override
	public CountryDataDTO getById(Integer id) {
		// TODO Auto-generated method stub
		CountryEntity entity = this.countryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pais", "id", id));
		CountryDataDTO dataDTO = this.countryMapper.mapEntityToDataDTO(entity);
		return dataDTO;
	}

	@Override
	public CountryDataDTO create(CountryRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		Boolean existNname = this.countryRepository.existsByName(requestDTO.getName());
		if(existNname) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		CountryEntity countryEntity = this.countryMapper.mapRequestToEntity(requestDTO);
		countryEntity.setCreatedAt(LocalDateTime.now());
		countryEntity.setUpdatedAt(LocalDateTime.now());
		
		CountryDataDTO dataCreated = this.countryMapper.mapEntityToDataDTO(this.countryRepository.save(countryEntity));			
		return dataCreated;
	}

	@Override
	public CountryDataDTO update(Integer id, CountryRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		CountryEntity dataCurrent = this.countryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pais", "id", id));
		Boolean existsName = this.countryRepository.existsByName(requestDTO.getName());
		Boolean diferentUsernameCurrent = (!requestDTO.getName().equalsIgnoreCase(dataCurrent.getName()));
		if(existsName && diferentUsernameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		dataCurrent.setName(requestDTO.getName().trim());
		dataCurrent.setUpdatedAt(LocalDateTime.now());
		
		CountryDataDTO dataUpdated = this.countryMapper.mapEntityToDataDTO(this.countryRepository.save(dataCurrent));	
		return dataUpdated;
	}

	@Override
	public CountryDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		CountryEntity entity = this.countryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pais", "id", id));
		this.countryRepository.delete(entity);
		CountryDataDTO dataDeleted = this.countryMapper.mapEntityToDataDTO(entity);
		return dataDeleted;
	}
	
}