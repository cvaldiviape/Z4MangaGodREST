package com.mangagod.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mangagod.dto.data.UserDataDTO;
import com.mangagod.dto.pagination.UserAllPageableDataDTO;
import com.mangagod.dto.request.UserCreateRequestDTO;
import com.mangagod.dto.request.UserUpdateRequestDTO;
import com.mangagod.entity.RoleEntity;
import com.mangagod.entity.UserEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.UserMapper;
import com.mangagod.repository.RoleRepository;
import com.mangagod.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserMapper userMapper;
	
	// ----------------------------------------------------------- services ----------------------------------------------------------- //
	@Override
	public UserAllPageableDataDTO getAll(Integer numberPage, Integer sizePage, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending();
	
		// agregando paginación
		Pageable pageable = PageRequest.of(numberPage, sizePage, sort);
		Page<UserEntity> usersPageable = this.userRepository.findAll(pageable);
		List<UserEntity> usersEntity = usersPageable.getContent();
		List<UserDataDTO> usersDto = usersEntity.stream().map((x) -> this.userMapper.mapEntityToDataDTO(x)).collect(Collectors.toList());	
		
		UserAllPageableDataDTO pageableDataDTO = new UserAllPageableDataDTO();
		pageableDataDTO.setUsers(usersDto);
		pageableDataDTO.setNumberPage(usersPageable.getNumber());
		pageableDataDTO.setSizePage(usersPageable.getSize());
		pageableDataDTO.setTotalElements(usersPageable.getTotalElements());
		pageableDataDTO.setTotalPages(usersPageable.getTotalPages());
		pageableDataDTO.setIsLastPage(usersPageable.isLast());
		
		return pageableDataDTO;
	}
	
	@Override
	public UserDataDTO getById(Integer id) {
		// TODO Auto-generated method stub
		UserEntity entity = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
		UserDataDTO dataDTO = this.userMapper.mapEntityToDataDTO(entity);
		return dataDTO;
	}

	@Override
	public UserDataDTO create(UserCreateRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		Boolean existsUsername = this.userRepository.existsByUsername(requestDTO.getUsername());
		Boolean existsEmail = this.userRepository.existsByEmail(requestDTO.getEmail());
		Boolean isEmptyArray = requestDTO.getRoleIds().isEmpty();
		if(existsUsername) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre de usuario " + requestDTO.getUsername() + " ya existe.");
		}else if(existsEmail) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El correo " + requestDTO.getEmail() +" ya existe.");
		}else if(isEmptyArray) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El array 'roleIds' debe contener al menos 1 elemento.");
		}
		UserEntity entity = this.userMapper.mapRequestToEntity(requestDTO);
		entity.setPassword(this.passwordEncoder.encode(requestDTO.getPassword())); // encriptando la contraseña

		Set<RoleEntity> roles = new HashSet<>();		
		for (Integer roleId : requestDTO.getRoleIds()) {
			RoleEntity roleEntity = this.roleRepository.findById(roleId).orElseThrow(() -> new MangaGodAppException(HttpStatus.BAD_REQUEST, "El rol con Id " + roleId + " no existe."));
			roles.add(roleEntity);
		}
		entity.setRoles(roles);
		
		UserDataDTO dataCreated = this.userMapper.mapEntityToDataDTO(this.userRepository.save(entity));		
		return dataCreated;
	}

	@Override
	public UserDataDTO update(Integer userId, UserUpdateRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		UserEntity dataCurrent = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		Boolean existsUsername = this.userRepository.existsByUsername(requestDTO.getUsername());
		Boolean existsEmail = this.userRepository.existsByEmail(requestDTO.getEmail());
		Boolean diferentUsernameCurrent = (!requestDTO.getUsername().equalsIgnoreCase(dataCurrent.getUsername()));
		Boolean diferentEmailCurrent = (!requestDTO.getEmail().equalsIgnoreCase(dataCurrent.getEmail()));
		Boolean arrayEmpty = requestDTO.getRoleIds().isEmpty();
		if(existsUsername && diferentUsernameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre de usuario " + requestDTO.getUsername() + " ya existe.");
		}else if(existsEmail && diferentEmailCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El correo " + requestDTO.getEmail() +" ya existe.");
		}else if(arrayEmpty) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "Es obligatorio asignar un Rol al usuario.");
		}
		dataCurrent.setUsername(requestDTO.getUsername());
		dataCurrent.setEmail(requestDTO.getEmail());
		
		Set<RoleEntity> roles = new HashSet<>();
		for (Integer roleId : requestDTO.getRoleIds()) {
			RoleEntity roleEntity = this.roleRepository.findById(roleId).orElseThrow(() -> new MangaGodAppException(HttpStatus.BAD_REQUEST, "El rol con Id " + roleId + " no existe."));
			roles.add(roleEntity);
		}
		dataCurrent.setRoles(roles);
		
		UserDataDTO dataUpdated = this.userMapper.mapEntityToDataDTO(this.userRepository.save(dataCurrent));	
		return dataUpdated;
	}

	@Override
	public UserDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		UserEntity entity = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
		this.userRepository.delete(entity);
		UserDataDTO dataDeleted = this.userMapper.mapEntityToDataDTO(entity);
		return dataDeleted;
	}
	
}