package com.mangagod.service;

import java.time.LocalDateTime;
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

import com.mangagod.dto.data.UserAllPageableDataDTO;
import com.mangagod.dto.data.UserDataDTO;
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
		List<UserDataDTO> usersDto = usersEntity.stream().map(user -> this.userMapper.mapUserEntityToUserDataDTO(user)).collect(Collectors.toList());	
		
		UserAllPageableDataDTO userAllPageableResponseDTO = new UserAllPageableDataDTO();
		userAllPageableResponseDTO.setUsers(usersDto);
		userAllPageableResponseDTO.setNumberPage(usersPageable.getNumber());
		userAllPageableResponseDTO.setSizePage(usersPageable.getSize());
		userAllPageableResponseDTO.setTotalElements(usersPageable.getTotalElements());
		userAllPageableResponseDTO.setTotalPages(usersPageable.getTotalPages());
		userAllPageableResponseDTO.setIsLastPage(usersPageable.isLast());
		
		return userAllPageableResponseDTO;
	}
	
	@Override
	public UserDataDTO getById(Integer id) {
		// TODO Auto-generated method stub
		UserEntity userEntity = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
		UserDataDTO userDataDTO = this.userMapper.mapUserEntityToUserDataDTO(userEntity);
		return userDataDTO;
	}

	@Override
	public UserDataDTO create(UserCreateRequestDTO createRequestDTO) {
		// TODO Auto-generated method stub
		Boolean existsUsername = this.userRepository.existsByUsername(createRequestDTO.getUsername());
		Boolean existsEmail = this.userRepository.existsByEmail(createRequestDTO.getEmail());
		Boolean isEmptyArray = createRequestDTO.getRoleIds().isEmpty();
		if(existsUsername) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre de usuario " + createRequestDTO.getUsername() + " ya existe.");
		}else if(existsEmail) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El correo " + createRequestDTO.getEmail() +" ya existe.");
		}else if(isEmptyArray) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El array 'roleIds' debe contener al menos 1 elemento.");
		}
		UserEntity userEntity = this.userMapper.mapUserCreateRequestToUserEntity(createRequestDTO);
		userEntity.setPassword(this.passwordEncoder.encode(createRequestDTO.getPassword())); // encriptando la contraseña
		userEntity.setCreatedAt(LocalDateTime.now());
		userEntity.setUpdatedAt(LocalDateTime.now());

		Set<RoleEntity> roles = new HashSet<>();		
		for (Integer roleId : createRequestDTO.getRoleIds()) {
			RoleEntity roleEntity = this.roleRepository.findById(roleId).orElseThrow(() -> new MangaGodAppException(HttpStatus.BAD_REQUEST, "El rol con Id " + roleId + " no existe."));
			roles.add(roleEntity);
		}
		userEntity.setRoles(roles);
		
		UserDataDTO userCreated = this.userMapper.mapUserEntityToUserDataDTO(this.userRepository.save(userEntity));		
		return userCreated;
	}

	@Override
	public UserDataDTO update(Integer userId, UserUpdateRequestDTO updateRequestDTO) {
		// TODO Auto-generated method stub
		UserEntity userDataCurrent = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		Boolean existsUsername = this.userRepository.existsByUsername(updateRequestDTO.getUsername());
		Boolean existsEmail = this.userRepository.existsByEmail(updateRequestDTO.getEmail());
		Boolean diferentUsernameCurrent = (!updateRequestDTO.getUsername().equalsIgnoreCase(userDataCurrent.getUsername()));
		Boolean diferentEmailCurrent = (!updateRequestDTO.getEmail().equalsIgnoreCase(userDataCurrent.getEmail()));
		Boolean arrayEmpty = updateRequestDTO.getRoleIds().isEmpty();
		if(existsUsername && diferentUsernameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre de usuario " + updateRequestDTO.getUsername() + " ya existe.");
		}else if(existsEmail && diferentEmailCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El correo " + updateRequestDTO.getEmail() +" ya existe.");
		}else if(arrayEmpty) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "Es obligatorio asignar un Rol al usuario.");
		}
		userDataCurrent.setUsername(updateRequestDTO.getUsername());
		userDataCurrent.setEmail(updateRequestDTO.getEmail());
		userDataCurrent.setUpdatedAt(LocalDateTime.now());
		
		Set<RoleEntity> roles = new HashSet<>();
		for (Integer roleId : updateRequestDTO.getRoleIds()) {
			RoleEntity roleEntity = this.roleRepository.findById(roleId).orElseThrow(() -> new MangaGodAppException(HttpStatus.BAD_REQUEST, "El rol con Id " + roleId + " no existe."));
			roles.add(roleEntity);
		}
		userDataCurrent.setRoles(roles);
		
		UserDataDTO userUpdated = this.userMapper.mapUserEntityToUserDataDTO(this.userRepository.save(userDataCurrent));	
		return userUpdated;
	}

	@Override
	public UserDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		UserEntity userEntity = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
		this.userRepository.delete(userEntity);
		UserDataDTO userDeleted = this.userMapper.mapUserEntityToUserDataDTO(userEntity);
		return userDeleted;
	}
	
}