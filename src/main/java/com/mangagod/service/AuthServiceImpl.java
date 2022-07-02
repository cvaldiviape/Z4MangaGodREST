package com.mangagod.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mangagod.dto.requestDto.AuthRequestDTO;
import com.mangagod.dto.responseDto.AuthResponseDTO;
import com.mangagod.entity.UserEntity;
import com.mangagod.repository.UserRepository;
import com.mangagod.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {
	
	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private ModelMapper modelMapper;
	
	// ----------------------------------------------------------- services ----------------------------------------------------------- //
	@Override
	public AuthResponseDTO login(AuthRequestDTO loginDTO) {
		// TODO Auto-generated method stub
		AuthResponseDTO loginAuthDTO = null;
		String token = null;
		
		token = this.getToken(loginDTO);
		
		if(token != null) {
		    UserEntity userEntity = this.userRepository.findByUsernameOrEmail(loginDTO.getUsernameOrEmail(), loginDTO.getUsernameOrEmail()).get();
		    loginAuthDTO = new AuthResponseDTO();
		    loginAuthDTO = this.mapLoginAuthDTO(userEntity);
		    loginAuthDTO.setTokenAcces(token);
			  
		}
		
		return loginAuthDTO;
	}
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	private AuthResponseDTO mapLoginAuthDTO(UserEntity userEntity) {
		AuthResponseDTO loginAuthDTO = this.modelMapper.map(userEntity, AuthResponseDTO.class);
		return loginAuthDTO;
	}

	// -------------------------------------------------------------- jwt ------------------------------------------------------------- //
	private String getToken(AuthRequestDTO loginDTO) {
		Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//obtenemos el token del jwtTokenProvider		
		return this.jwtTokenProvider.generateToken(authentication);
	} 
	
}