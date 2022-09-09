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

import com.mangagod.dto.data.CharacterDataDTO;
import com.mangagod.dto.pagination.CharacterAllPageableDataDTO;
import com.mangagod.dto.request.CharacterRequestDTO;
import com.mangagod.entity.CharacterEntity;
import com.mangagod.entity.StoryEntity;
import com.mangagod.entity.TypeCharacterEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.CharacterMapper;
import com.mangagod.repository.CharacterRepository;
import com.mangagod.repository.StoryRepository;
import com.mangagod.repository.TypeCharacterRepository;

@Service
@Transactional
public class CharacterServiceImpl implements CharacterService {

	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private CharacterRepository characterRepository;
	@Autowired
	private StoryRepository storyRepository;
	@Autowired
	private TypeCharacterRepository typeCharacterRepository;	
	@Autowired
	private CharacterMapper characterMapper;
		
	// ----------------------------------------------------------- services ----------------------------------------------------------- //
	@Override
	public CharacterAllPageableDataDTO getAll(Integer numberPage, Integer sizePage, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending();
	
		// agregando paginación
		Pageable pageable = PageRequest.of(numberPage, sizePage, sort);
		Page<CharacterEntity> charactersPageable = this.characterRepository.findAll(pageable);	
		List<CharacterEntity> charactersEntity = charactersPageable.getContent();
		List<CharacterDataDTO> charactersDTO = charactersEntity.stream().map((x) -> this.characterMapper.mapEntityToDataDTO(x)).collect(Collectors.toList());	
		
		CharacterAllPageableDataDTO pageableDataDTO = new CharacterAllPageableDataDTO();
		pageableDataDTO.setCharacters(charactersDTO);
		pageableDataDTO.setNumberPage(charactersPageable.getNumber());
		pageableDataDTO.setSizePage(charactersPageable.getSize());
		pageableDataDTO.setTotalElements(charactersPageable.getTotalElements());
		pageableDataDTO.setTotalPages(charactersPageable.getTotalPages());
		pageableDataDTO.setIsLastPage(charactersPageable.isLast());
		
		return pageableDataDTO;
	}

	@Override
	public CharacterDataDTO getById(Integer id) {
		// TODO Auto-generated method stub
		CharacterEntity entity = this.characterRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", id));
		CharacterDataDTO dataDTO = this.characterMapper.mapEntityToDataDTO(entity);
		return dataDTO;
	}

	@Override
	public CharacterDataDTO create(CharacterRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		StoryEntity storyEntity = this.storyRepository.findById(requestDTO.getStoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Historieta", "id", requestDTO.getStoryId()));
		TypeCharacterEntity typeCharacterEntity = this.typeCharacterRepository.findById(requestDTO.getTypeId())
				.orElseThrow(() -> new ResourceNotFoundException("Tipo de personaje", "id", requestDTO.getTypeId()));
		
		Boolean existName = this.characterRepository.existsByName(requestDTO.getName());
		if(existName) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
			
		CharacterEntity entity = this.characterMapper.mapRequestToEntity(requestDTO);
		entity.setStory(storyEntity);
		entity.setType(typeCharacterEntity);
		
		CharacterDataDTO dataCreated = this.characterMapper.mapEntityToDataDTO(this.characterRepository.save(entity));			
		return dataCreated;
	}

	@Override
	public CharacterDataDTO update(Integer id, CharacterRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		CharacterEntity dataCurrent = this.characterRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", id));
		StoryEntity storyEntity = this.storyRepository.findById(requestDTO.getStoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Historieta", "id", requestDTO.getStoryId()));
		TypeCharacterEntity typeCharacterEntity = this.typeCharacterRepository.findById(requestDTO.getTypeId())
				.orElseThrow(() -> new ResourceNotFoundException("Tipo de personaje", "id", requestDTO.getTypeId()));
		
		Boolean existsName = this.characterRepository.existsByName(requestDTO.getName());
		Boolean diferentNameCurrent = (!requestDTO.getName().equalsIgnoreCase(dataCurrent.getName()));
		if(existsName && diferentNameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		dataCurrent.setName(requestDTO.getName());
		dataCurrent.setDescription(requestDTO.getDescription());
		dataCurrent.setUrlImage(requestDTO.getUrlImage());
		dataCurrent.setStory(storyEntity);
		dataCurrent.setType(typeCharacterEntity);
		
		CharacterDataDTO dataUpdated = this.characterMapper.mapEntityToDataDTO(this.characterRepository.save(dataCurrent));	
		return dataUpdated;
	}

	@Override
	public CharacterDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		CharacterEntity entity = this.characterRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", id));
		this.characterRepository.delete(entity);
		CharacterDataDTO dataDeleted = this.characterMapper.mapEntityToDataDTO(entity);
		return dataDeleted;
	}

}