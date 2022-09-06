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
import com.mangagod.dto.data.RoleDataDTO;
import com.mangagod.dto.pagination.RoleAllPageableDataDTO;
import com.mangagod.dto.request.RoleRequestDTO;
import com.mangagod.entity.RoleEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.RoleMapper;
import com.mangagod.repository.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	
	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RoleMapper roleMapper;

	// ----------------------------------------------------------- services ----------------------------------------------------------- //
	@Override
	public RoleAllPageableDataDTO getAll(Integer numberPage, Integer sizePage, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending();
	
		// agregando paginación
		Pageable pageable = PageRequest.of(numberPage, sizePage, sort);
		Page<RoleEntity> rolesPageable = this.roleRepository.findAll(pageable);
		List<RoleEntity> rolesEntity = rolesPageable.getContent();
		List<RoleDataDTO> rolesDto = rolesEntity.stream().map((x) -> this.roleMapper.mapEntityToDataDTO(x)).collect(Collectors.toList());	
		
		RoleAllPageableDataDTO pageableDataDTO = new RoleAllPageableDataDTO();
		pageableDataDTO.setRoles(rolesDto);
		pageableDataDTO.setNumberPage(rolesPageable.getNumber());
		pageableDataDTO.setSizePage(rolesPageable.getSize());
		pageableDataDTO.setTotalElements(rolesPageable.getTotalElements());
		pageableDataDTO.setTotalPages(rolesPageable.getTotalPages());
		pageableDataDTO.setIsLastPage(rolesPageable.isLast());
		
		return pageableDataDTO;
	}

	@Override
	public RoleDataDTO getById(Integer id) {
		// TODO Auto-generated method stub
		RoleEntity entity = this.roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));
		RoleDataDTO dataDTO = this.roleMapper.mapEntityToDataDTO(entity);
		return dataDTO;
	}

	@Override
	public RoleDataDTO create(RoleRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		requestDTO.setName("ROLE_" + requestDTO.getName().toUpperCase());
		
		Boolean existsName = this.roleRepository.existsByName(requestDTO.getName());
		if(existsName) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		RoleEntity entity = this.roleMapper.mapRequestToEntity(requestDTO);
		
		RoleDataDTO dataCreated = this.roleMapper.mapEntityToDataDTO(this.roleRepository.save(entity));			
		return dataCreated;
	}

	@Override
	public RoleDataDTO update(Integer id, RoleRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		requestDTO.setName("ROLE_" + requestDTO.getName().toUpperCase());
		
		RoleEntity dataCurrent = this.roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));
		Boolean existsName = this.roleRepository.existsByName(requestDTO.getName());
		Boolean diferentNnameCurrent = (!requestDTO.getName().equalsIgnoreCase(dataCurrent.getName()));
		if(existsName && diferentNnameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		dataCurrent.setName(requestDTO.getName());
		dataCurrent.setDescription(requestDTO.getDescription());
		
		RoleDataDTO dataUpdated = this.roleMapper.mapEntityToDataDTO(this.roleRepository.save(dataCurrent));	
		return dataUpdated;
	}

	@Override
	public RoleDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		RoleEntity entity = this.roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));
		this.roleRepository.delete(entity);
		RoleDataDTO dataDeleted = this.roleMapper.mapEntityToDataDTO(entity);
		return dataDeleted;
	}

}