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

import com.mangagod.dto.pagination.RoleAllPageableDataDTO;
import com.mangagod.dto.request.RoleRequestDTO;
import com.mangagod.dto.response.RoleResponseDTO;
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
		List<RoleResponseDTO> rolesDto = rolesEntity.stream().map((x) -> this.roleMapper.mapEntityToResponseDTO(x)).collect(Collectors.toList());	
		
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
	public RoleResponseDTO getById(Integer id) {
		// TODO Auto-generated method stub
		RoleEntity entity = this.getRoleById(id);
		RoleResponseDTO dataDTO = this.roleMapper.mapEntityToResponseDTO(entity);
		return dataDTO;
	}

	@Override
	public RoleResponseDTO create(RoleRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		requestDTO.setName("ROLE_" + requestDTO.getName().toUpperCase());
		this.verifyNameUnique(requestDTO.getName());
		RoleEntity entity = this.roleMapper.mapRequestToEntity(requestDTO);
		RoleResponseDTO dataCreated = this.roleMapper.mapEntityToResponseDTO(this.roleRepository.save(entity));			
		return dataCreated;
	}

	@Override
	public RoleResponseDTO update(Integer id, RoleRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		requestDTO.setName("ROLE_" + requestDTO.getName().toUpperCase());
		RoleEntity dataCurrent = this.getRoleById(id);
		this.verifyNameUnique(requestDTO.getName(), dataCurrent.getName());
		dataCurrent.setName(requestDTO.getName());
		dataCurrent.setDescription(requestDTO.getDescription());
		RoleResponseDTO dataUpdated = this.roleMapper.mapEntityToResponseDTO(this.roleRepository.save(dataCurrent));	
		return dataUpdated;
	}

	@Override
	public RoleResponseDTO delete(Integer id) {
		// TODO Auto-generated method stub
		RoleEntity entity = this.getRoleById(id);
		this.roleRepository.delete(entity);
		RoleResponseDTO dataDeleted = this.roleMapper.mapEntityToResponseDTO(entity);
		return dataDeleted;
	}
	
	// ----------------------------------------------------------- utils ----------------------------------------------------------- ((
	public RoleEntity getRoleById(Integer id) {
		return this.roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));
	}
	
	public void verifyNameUnique(String name) {
		Boolean existName = this.roleRepository.existsByName(name);
		if(existName) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El name " + name + " ya existe.");
		}
	}
	
	public void verifyNameUnique(String name, String nameCurrent) {
		Boolean existName = this.roleRepository.existsByName(name);
		Boolean diferentNameCurrent = (!name.equalsIgnoreCase(nameCurrent));
		if(existName && diferentNameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El name " + name + " ya existe.");
		}
	}

}