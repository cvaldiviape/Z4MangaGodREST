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
import com.mangagod.dto.data.TypeCharacterDataDTO;
import com.mangagod.dto.pagination.TypeCharacterAllPageableDataDTO;
import com.mangagod.dto.request.TypeCharacterRequestDTO;
import com.mangagod.entity.TypeCharacterEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.TypeCharacterMapper;
import com.mangagod.repository.TypeCharacterRepository;

@Service
@Transactional
public class TypeCharacterServiceImpl implements TypeCharacterService {

	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private TypeCharacterRepository typeCharacterRepository;
	@Autowired
	private TypeCharacterMapper typeCharacterMapper;
	
	// ----------------------------------------------------------- services ----------------------------------------------------------- //
	@Override
	public TypeCharacterAllPageableDataDTO getAll(Integer numberPage, Integer sizePage, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending();
	
		// agregando paginación
		Pageable pageable = PageRequest.of(numberPage, sizePage, sort);
		Page<TypeCharacterEntity> typeCharactersPageable = this.typeCharacterRepository.findAll(pageable);	
		List<TypeCharacterEntity> typeCharactersEntity = typeCharactersPageable.getContent();
		List<TypeCharacterDataDTO> typeCharacteresDTO = typeCharactersEntity.stream().map((x) -> this.typeCharacterMapper.mapEntityToDataDTO(x)).collect(Collectors.toList());	
		
		TypeCharacterAllPageableDataDTO pageableDataDTO = new TypeCharacterAllPageableDataDTO();
		pageableDataDTO.setTypeCharacters(typeCharacteresDTO);
		pageableDataDTO.setNumberPage(typeCharactersPageable.getNumber());
		pageableDataDTO.setSizePage(typeCharactersPageable.getSize());
		pageableDataDTO.setTotalElements(typeCharactersPageable.getTotalElements());
		pageableDataDTO.setTotalPages(typeCharactersPageable.getTotalPages());
		pageableDataDTO.setIsLastPage(typeCharactersPageable.isLast());
		
		return pageableDataDTO;
	}

	@Override
	public TypeCharacterDataDTO getById(Integer id) {
		// TODO Auto-generated method stub
		TypeCharacterEntity entity = this.typeCharacterRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tipo de personaje", "id", id));
		TypeCharacterDataDTO dataDTO = this.typeCharacterMapper.mapEntityToDataDTO(entity);
		return dataDTO;
	}

	@Override
	public TypeCharacterDataDTO create(TypeCharacterRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		Boolean existNname = this.typeCharacterRepository.existsByName(requestDTO.getName());
		if(existNname) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		TypeCharacterEntity entity = this.typeCharacterMapper.mapRequestToEntity(requestDTO);
		
		TypeCharacterDataDTO dataCreated = this.typeCharacterMapper.mapEntityToDataDTO(this.typeCharacterRepository.save(entity));			
		return dataCreated;
	}

	@Override
	public TypeCharacterDataDTO update(Integer id, TypeCharacterRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		TypeCharacterEntity dataCurrent = this.typeCharacterRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tipo de personaje", "id", id));
		Boolean existsName = this.typeCharacterRepository.existsByName(requestDTO.getName());
		Boolean diferentNameCurrent = (!requestDTO.getName().equalsIgnoreCase(dataCurrent.getName()));
		if(existsName && diferentNameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		dataCurrent.setName(requestDTO.getName().trim());
		
		TypeCharacterDataDTO dataUpdated = this.typeCharacterMapper.mapEntityToDataDTO(this.typeCharacterRepository.save(dataCurrent));	
		return dataUpdated;
	}

	@Override
	public TypeCharacterDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		TypeCharacterEntity entity = this.typeCharacterRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tipo de personaje", "id", id));
		this.typeCharacterRepository.delete(entity);
		TypeCharacterDataDTO dataDeleted = this.typeCharacterMapper.mapEntityToDataDTO(entity);
		return dataDeleted;
	}

}