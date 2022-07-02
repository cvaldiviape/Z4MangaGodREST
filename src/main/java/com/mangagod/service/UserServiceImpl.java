package com.mangagod.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mangagod.dto.requestDto.UserRequestDTO;
import com.mangagod.dto.responseDto.UserResponseDTO;
import com.mangagod.entity.RoleEntity;
import com.mangagod.entity.UserEntity;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.repository.RoleRepository;
import com.mangagod.repository.UserRepository;

@Service
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
	public UserResponseDTO getUserById(Integer id) {
		// TODO Auto-generated method stub
		UserEntity userEntity = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		UserResponseDTO userResponseDTO = this.mapUserResponseDTO(userEntity);
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
		// TODO Auto-generated method stub
		UserResponseDTO userResponseDTO = null;
		
		if(!this.userRepository.existsByUsername(userRequestDTO.getUsername()) && !this.userRepository.existsByEmail(userRequestDTO.getEmail())) {
			UserEntity userEntity = this.mapUserEntity(userRequestDTO);
			userEntity.setPassword(this.passwordEncoder.encode(userRequestDTO.getPassword())); // encriptando la contraseña
			userEntity.setCreatedAt(LocalDateTime.now());
			userEntity.setUpdatedAt(LocalDateTime.now());
			
			if(!userRequestDTO.getRoleIds().isEmpty()) {
				Set<RoleEntity> roles = new HashSet<>();
				
				for (Integer roleId : userRequestDTO.getRoleIds()) {
					RoleEntity roleEntity = this.roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));
					roles.add(roleEntity);
				}
				userEntity.setRoles(roles);
			}
			userResponseDTO = this.mapUserResponseDTO(this.userRepository.save(userEntity));	
		}
		
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO updateUser(UserRequestDTO userDTO, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDTO deleteUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	private UserResponseDTO mapUserResponseDTO(UserEntity userEntity) {
		UserResponseDTO userResponseDTO = this.modelMapper.map(userEntity, UserResponseDTO.class);
		return userResponseDTO;
	}
	
	private UserEntity mapUserEntity(UserRequestDTO userRequestDTO) {
		UserEntity userEntity = this.modelMapper.map(userRequestDTO, UserEntity.class);
		return userEntity;
	}

}