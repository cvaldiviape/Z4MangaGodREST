package com.mangagod.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mangagod.dto.requestDto.UserCreateRequestDTO;
import com.mangagod.dto.requestDto.UserUpdateRequestDTO;
import com.mangagod.dto.responseDto.UserAllPageableResponseDTO;
import com.mangagod.dto.responseDto.UserResponseDTO;
import com.mangagod.entity.RoleEntity;
import com.mangagod.entity.UserEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
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
	private ModelMapper modelMapper;
	
	// ----------------------------------------------------------- services ----------------------------------------------------------- //
	@Override
	public UserAllPageableResponseDTO getAllUsers(Integer numberPage, Integer sizePage, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending();
	
		// agregando paginación
		Pageable pageable = PageRequest.of(numberPage, sizePage, sort);
		
		Page<UserEntity> usersPageable = this.userRepository.findAll(pageable);
		
		List<UserEntity> users = usersPageable.getContent();
		
		List<UserResponseDTO> content = users.stream().map(user -> this.mapUserEntityToUserResponseDTO(user)).collect(Collectors.toList());	
		
		UserAllPageableResponseDTO userAllPageableResponseDTO = new UserAllPageableResponseDTO();
		
		userAllPageableResponseDTO.setUsers(content);
		userAllPageableResponseDTO.setNumberPage(usersPageable.getNumber());
		userAllPageableResponseDTO.setSizePage(usersPageable.getSize());
		userAllPageableResponseDTO.setTotalElements(usersPageable.getTotalElements());
		userAllPageableResponseDTO.setTotalPages(usersPageable.getTotalPages());
		userAllPageableResponseDTO.setIsLastPage(usersPageable.isLast());
		
		return userAllPageableResponseDTO;
	}
	
	@Override
	public UserResponseDTO getUserById(Integer id) {
		// TODO Auto-generated method stub
		UserEntity userEntity = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		UserResponseDTO userResponseDTO = this.mapUserEntityToUserResponseDTO(userEntity);
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO createUser(UserCreateRequestDTO userCreateRequestDTO) {
		// TODO Auto-generated method stub
		UserResponseDTO userResponseDTO = null;
		
		Boolean existsUsername = this.userRepository.existsByUsername(userCreateRequestDTO.getUsername());
		Boolean existsEmail = this.userRepository.existsByEmail(userCreateRequestDTO.getEmail());
		Boolean arrayEmpty = userCreateRequestDTO.getRoleIds().isEmpty();
		
		if(existsUsername) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El username " + userCreateRequestDTO.getUsername() + " ya existe.");
		}else if(existsEmail) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El email " + userCreateRequestDTO.getEmail() +" ya existe.");
		}else if(arrayEmpty) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El array 'roleIds' debe contener al menos 1 elemento.");
		}
		
		UserEntity userEntity = this.mapUserCreateRequestToEntity(userCreateRequestDTO);
		userEntity.setPassword(this.passwordEncoder.encode(userCreateRequestDTO.getPassword())); // encriptando la contraseña
		userEntity.setCreatedAt(LocalDateTime.now());
		userEntity.setUpdatedAt(LocalDateTime.now());
		
		Set<RoleEntity> roles = new HashSet<>();
		
		for (Integer roleId : userCreateRequestDTO.getRoleIds()) {
			RoleEntity roleEntity = this.roleRepository.findById(roleId).orElseThrow(() -> new MangaGodAppException(HttpStatus.BAD_REQUEST, "El rol con Id " + roleId + " no existe."));
			roles.add(roleEntity);
		}
		userEntity.setRoles(roles);
		
		userResponseDTO = this.mapUserEntityToUserResponseDTO(this.userRepository.save(userEntity));	
			
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO updateUser(Integer userId, UserUpdateRequestDTO userUpdateRequestDTO) {
		// TODO Auto-generated method stub
		UserResponseDTO userResponseDTO = null;
		
		UserEntity userDataCurrent = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		Boolean existsUsername = this.userRepository.existsByUsername(userUpdateRequestDTO.getUsername());
		Boolean existsEmail = this.userRepository.existsByEmail(userUpdateRequestDTO.getEmail());
		Boolean diferentUsernameCurrent = (!userUpdateRequestDTO.getUsername().equalsIgnoreCase(userDataCurrent.getUsername()));
		Boolean diferentEmailCurrent = (!userUpdateRequestDTO.getEmail().equalsIgnoreCase(userDataCurrent.getEmail()));
		Boolean arrayEmpty = userUpdateRequestDTO.getRoleIds().isEmpty();
		
		if(existsUsername && diferentUsernameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El username " + userUpdateRequestDTO.getUsername() + " ya existe.");
		}else if(existsEmail && diferentEmailCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El email " + userUpdateRequestDTO.getEmail() +" ya existe.");
		}else if(arrayEmpty) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El array 'roleIds' debe contener al menos 1 elemento.");
		}
		
		userDataCurrent.setUsername(userUpdateRequestDTO.getUsername());
		userDataCurrent.setEmail(userUpdateRequestDTO.getEmail());
		userDataCurrent.setUpdatedAt(LocalDateTime.now());
		
		Set<RoleEntity> roles = new HashSet<>();
		
		for (Integer roleId : userUpdateRequestDTO.getRoleIds()) {
			RoleEntity roleEntity = this.roleRepository.findById(roleId).orElseThrow(() -> new MangaGodAppException(HttpStatus.BAD_REQUEST, "El rol con Id " + roleId + " no existe."));
			roles.add(roleEntity);
		}
		
		userDataCurrent.setRoles(roles);	
		userResponseDTO = this.mapUserEntityToUserResponseDTO(this.userRepository.save(userDataCurrent));	
		
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepository.delete(userEntity);
		UserResponseDTO userResponseDTO = this.mapUserEntityToUserResponseDTO(userEntity);
		return userResponseDTO;
	}
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	private UserResponseDTO mapUserEntityToUserResponseDTO(UserEntity userEntity) {
		UserResponseDTO userResponseDTO = this.modelMapper.map(userEntity, UserResponseDTO.class);
		return userResponseDTO;
	}
	
	private UserEntity mapUserCreateRequestToEntity(UserCreateRequestDTO userRequestDTO) {
		UserEntity userEntity = this.modelMapper.map(userRequestDTO, UserEntity.class);
		return userEntity;
	}
	
}