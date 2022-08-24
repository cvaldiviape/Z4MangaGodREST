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
import com.mangagod.dto.data.RoleAllPageableDataDTO;
import com.mangagod.dto.data.RoleDataDTO;
import com.mangagod.dto.request.RoleCreateRequestDTO;
import com.mangagod.dto.request.RoleUpdateRequestDTO;
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
		List<RoleDataDTO> rolesDto = rolesEntity.stream().map(role -> this.roleMapper.mapRoleEntityToRoleDataDTO(role)).collect(Collectors.toList());	
		
		RoleAllPageableDataDTO roleAllPageableDataDTO = new RoleAllPageableDataDTO();
		roleAllPageableDataDTO.setRoles(rolesDto);
		roleAllPageableDataDTO.setNumberPage(rolesPageable.getNumber());
		roleAllPageableDataDTO.setSizePage(rolesPageable.getSize());
		roleAllPageableDataDTO.setTotalElements(rolesPageable.getTotalElements());
		roleAllPageableDataDTO.setTotalPages(rolesPageable.getTotalPages());
		roleAllPageableDataDTO.setIsLastPage(rolesPageable.isLast());
		
		return roleAllPageableDataDTO;
	}

	@Override
	public RoleDataDTO getById(Integer id) {
		// TODO Auto-generated method stub
		RoleEntity roleEntity = this.roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));
		RoleDataDTO roleDataDTO = this.roleMapper.mapRoleEntityToRoleDataDTO(roleEntity);
		return roleDataDTO;
	}

	@Override
	public RoleDataDTO create(RoleCreateRequestDTO createRequestDTO) {
		// TODO Auto-generated method stub
		String name = createRequestDTO.getName();
		if(createRequestDTO.getName().isEmpty()) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El campo nombre es obligatorio.");
		}
		if(createRequestDTO.getDescription().isEmpty()) {
			createRequestDTO.setDescription(createRequestDTO.getName().toUpperCase());
		}
		createRequestDTO.setName("ROLE_" + createRequestDTO.getName().toUpperCase());
		createRequestDTO.setDescription(createRequestDTO.getDescription().toUpperCase());
		
		Boolean existsName = this.roleRepository.existsByName(createRequestDTO.getName());
		if(existsName) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + name + " ya existe.");
		}
		RoleEntity roleEntity = this.roleMapper.mapRoleCreateRequestToRoleEntity(createRequestDTO);
		roleEntity.setCreatedAt(LocalDateTime.now());
		roleEntity.setUpdatedAt(LocalDateTime.now());
		
		RoleDataDTO roleCreated = this.roleMapper.mapRoleEntityToRoleDataDTO(this.roleRepository.save(roleEntity));			
		return roleCreated;
	}

	@Override
	public RoleDataDTO update(Integer id, RoleUpdateRequestDTO updateRequestDTO) {
		// TODO Auto-generated method stub
		String name = updateRequestDTO.getName();
		if(updateRequestDTO.getName().isEmpty()) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El campo nombre es obligatorio.");
		}
		if(updateRequestDTO.getDescription().isEmpty()) {
			updateRequestDTO.setDescription(updateRequestDTO.getName().toUpperCase());
		}
		updateRequestDTO.setName("ROLE_" + updateRequestDTO.getName().toUpperCase());
		updateRequestDTO.setDescription(updateRequestDTO.getDescription().toUpperCase());
		
		RoleEntity roleDataCurrent = this.roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));
		Boolean existsName = this.roleRepository.existsByName(updateRequestDTO.getName());
		Boolean diferentUsernameCurrent = (!updateRequestDTO.getName().equalsIgnoreCase(roleDataCurrent.getName()));
		if(existsName && diferentUsernameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + name + " ya existe.");
		}
		roleDataCurrent.setName(updateRequestDTO.getName());
		roleDataCurrent.setDescription(updateRequestDTO.getDescription());
		roleDataCurrent.setUpdatedAt(LocalDateTime.now());
		
		RoleDataDTO roleUpdated = this.roleMapper.mapRoleEntityToRoleDataDTO(this.roleRepository.save(roleDataCurrent));	
		return roleUpdated;
	}

	@Override
	public RoleDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		RoleEntity roleEntity = this.roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));
		this.roleRepository.delete(roleEntity);
		RoleDataDTO roleDeleted = this.roleMapper.mapRoleEntityToRoleDataDTO(roleEntity);
		return roleDeleted;
	}

}