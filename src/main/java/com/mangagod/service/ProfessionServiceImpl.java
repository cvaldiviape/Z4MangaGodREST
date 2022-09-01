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
import com.mangagod.dto.data.ProfessionDataDTO;
import com.mangagod.dto.pagination.ProfessionAllPageableDataDTO;
import com.mangagod.dto.request.ProfessionRequestDTO;
import com.mangagod.entity.ProfessionEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.ProfessionMapper;
import com.mangagod.repository.ProfessionRepository;

@Service
@Transactional
public class ProfessionServiceImpl implements ProfessionService {

	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private ProfessionRepository professionRepository;
	@Autowired
	private ProfessionMapper professionMapper;
		
	// ----------------------------------------------------------- services ----------------------------------------------------------- //
	@Override
	public ProfessionAllPageableDataDTO getAll(Integer numberPage, Integer sizePage, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending();
	
		// agregando paginación
		Pageable pageable = PageRequest.of(numberPage, sizePage, sort);
		Page<ProfessionEntity> professionsPageable = this.professionRepository.findAll(pageable);	
		List<ProfessionEntity> professionsEntity = professionsPageable.getContent();
		List<ProfessionDataDTO> professionsDTO = professionsEntity.stream().map((x) -> this.professionMapper.mapEntityToDataDTO(x)).collect(Collectors.toList());	
		
		ProfessionAllPageableDataDTO pageableDataDTO = new ProfessionAllPageableDataDTO();
		pageableDataDTO.setProfessions(professionsDTO);
		pageableDataDTO.setNumberPage(professionsPageable.getNumber());
		pageableDataDTO.setSizePage(professionsPageable.getSize());
		pageableDataDTO.setTotalElements(professionsPageable.getTotalElements());
		pageableDataDTO.setTotalPages(professionsPageable.getTotalPages());
		pageableDataDTO.setIsLastPage(professionsPageable.isLast());
		
		return pageableDataDTO;
	}

	@Override
	public ProfessionDataDTO getById(Integer id) {
		// TODO Auto-generated method stub
		ProfessionEntity entity = this.professionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Profesión", "id", id));
		ProfessionDataDTO dataDTO = this.professionMapper.mapEntityToDataDTO(entity);
		return dataDTO;
	}

	@Override
	public ProfessionDataDTO create(ProfessionRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		Boolean existNname = this.professionRepository.existsByName(requestDTO.getName());
		if(existNname) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		ProfessionEntity entity = this.professionMapper.mapRequestToEntity(requestDTO);
		entity.setCreatedAt(LocalDateTime.now());
		entity.setUpdatedAt(LocalDateTime.now());
		
		ProfessionDataDTO dataCreated = this.professionMapper.mapEntityToDataDTO(this.professionRepository.save(entity));			
		return dataCreated;
	}

	@Override
	public ProfessionDataDTO update(Integer id, ProfessionRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		ProfessionEntity dataCurrent = this.professionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Profesión", "id", id));
		Boolean existsUsername = this.professionRepository.existsByName(requestDTO.getName());
		Boolean diferentUsernameCurrent = (!requestDTO.getName().equalsIgnoreCase(dataCurrent.getName()));
		if(existsUsername && diferentUsernameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		dataCurrent.setName(requestDTO.getName());
		dataCurrent.setUpdatedAt(LocalDateTime.now());
		
		ProfessionDataDTO dataUpdated = this.professionMapper.mapEntityToDataDTO(this.professionRepository.save(dataCurrent));	
		return dataUpdated;
	}

	@Override
	public ProfessionDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		ProfessionEntity entity = this.professionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Profesión", "id", id));
		this.professionRepository.delete(entity);
		ProfessionDataDTO dataDeleted = this.professionMapper.mapEntityToDataDTO(entity);
		return dataDeleted;
	}

}