package com.mangagod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mangagod.dto.data.AuthDataDTO;
import com.mangagod.dto.data.RoleDataDTO;
import com.mangagod.dto.data.TokenDataDTO;
import com.mangagod.dto.data.UserAuthDataDTO;
import com.mangagod.dto.request.AuthRequestDTO;
import com.mangagod.dto.request.TokenRequestDTO;
import com.mangagod.entity.RoleEntity;
import com.mangagod.entity.UserEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.AuthMapper;
import com.mangagod.mapper.RoleMapper;
import com.mangagod.repository.RoleRepository;
import com.mangagod.repository.UserRepository;
import com.mangagod.security.JwtTokenProvider;
import com.mangagod.util.AppSettingProperties;

@Service
public class AuthServiceImpl implements AuthService {

	// ----------------------------------------------------- dependency injection ----------------------------------------------------- //
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private AuthMapper authMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private AppSettingProperties appSettingProperties;
	
	// ----------------------------------------------------------- services ----------------------------------------------------------- //
	@Override
	public AuthDataDTO login(AuthRequestDTO authRequestDTO) {
		// TODO Auto-generated method stub
		String usernameOrEmail = authRequestDTO.getUsernameOrEmail();
		Integer roleId = authRequestDTO.getRoleId();

		String token = this.jwtTokenProvider.getToken(authRequestDTO)
				.orElseThrow(() -> new MangaGodAppException(HttpStatus.INTERNAL_SERVER_ERROR, "Bad credentials"));
		UserEntity userEntity = this.userRepository.findByUsernameOrEmailAndRoleId(roleId, usernameOrEmail)
				.orElseThrow(() -> new MangaGodAppException(HttpStatus.INTERNAL_SERVER_ERROR, "Bad credentials"));
		RoleEntity roleEntity = this.roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));
		
		UserAuthDataDTO userAuthDataDTO = this.authMapper.mapUserEntityToUserAuthDataDTO(userEntity);
		RoleDataDTO roleDataDTO = this.roleMapper.mapEntityToDataDTO(roleEntity);
		TokenDataDTO tokenDataDTO = new TokenDataDTO();
		tokenDataDTO.setTokenAccess(token);
		tokenDataDTO.setTokenType(this.appSettingProperties.JWT_TYPE);
		tokenDataDTO.setTokenExpiredIn(this.appSettingProperties.JWT_EXPIRATION_IN_MLS);

		AuthDataDTO authDataDto = new AuthDataDTO();
		authDataDto.setUser(userAuthDataDTO);
		authDataDto.setRole(roleDataDTO);
		authDataDto.setToken(tokenDataDTO);
		
		return authDataDto;
	}

	@Override
	public String refreshToken(TokenRequestDTO tokenRequestDTO) {
		String tokenRefreshed = this.jwtTokenProvider.refreshToken(tokenRequestDTO.getTokenAccess())
				.orElseThrow(() -> new MangaGodAppException(HttpStatus.INTERNAL_SERVER_ERROR, "Bad credentials"));
		return tokenRefreshed;
	}

}