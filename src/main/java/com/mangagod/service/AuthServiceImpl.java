package com.mangagod.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.mangagod.dto.requestDto.AuthRequestDTO;
import com.mangagod.dto.requestDto.TokenRequestDTO;
import com.mangagod.dto.responseDto.AuthResponseDTO;
import com.mangagod.dto.responseDto.RoleResponseDTO;
import com.mangagod.dto.responseDto.TokenResponseDTO;
import com.mangagod.entity.RoleEntity;
import com.mangagod.entity.UserEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.repository.RoleRepository;
import com.mangagod.repository.UserRepository;
import com.mangagod.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {

	// ----------------------------------------------------- dependency injection ----------------------------------------------------- //
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private ModelMapper modelMapper;
	@Value("${app.jwt-expiration-milliseconds}") // obtendre el valor definido en el archivo "application.porperties"
	private Integer jwtExpirationInMs;
	@Value("${app.jwt-type}") // obtendre el valor definido en el archivo "application.porperties"
	private String jwtType;

	// ----------------------------------------------------------- services ----------------------------------------------------------- //
	@Override
	public Map<String, Object> login(AuthRequestDTO authRequestDTO) {
		// TODO Auto-generated method stub
		Map<String, Object> dataAuth = null;
		AuthResponseDTO authResponseDTO = null;
		TokenResponseDTO tokenResponseDTO = null;
		RoleResponseDTO roleResponseDTO = null;
		UserEntity userEntity = null;
		RoleEntity roleEntity = null;
		String token = null;

		String usernameOrEmail = authRequestDTO.getUsernameOrEmail();
		Integer roleId = authRequestDTO.getRoleId();

		token = this.getToken(authRequestDTO);
		userEntity = this.userRepository.findByUsernameOrEmailAndRoleId(roleId, usernameOrEmail)
				.orElseThrow(() -> new MangaGodAppException(HttpStatus.INTERNAL_SERVER_ERROR, "Bad credentials"));
		roleEntity = this.roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));

		if (token != null && userEntity != null && roleEntity != null) {

			authResponseDTO = new AuthResponseDTO();
			authResponseDTO = this.mapUserEntityToAuthResponseDTO(userEntity);

			roleResponseDTO = new RoleResponseDTO();
			roleResponseDTO = this.mapRoleEntityToRoleResponseDTO(roleEntity);

			tokenResponseDTO = new TokenResponseDTO();
			tokenResponseDTO.setTokenAccess(token);
			tokenResponseDTO.setTokenType(jwtType);
			tokenResponseDTO.setTokenExpiredIn(jwtExpirationInMs);

			dataAuth = new HashMap<String, Object>();
			dataAuth.put("user", authResponseDTO);
			dataAuth.put("role", roleResponseDTO);
			dataAuth.put("token", tokenResponseDTO);
		}

		return dataAuth;
	}

	@Override
	public String refreshToken(TokenRequestDTO tokenRequestDTO) throws ParseException {
		String tokenRefreshed = this.jwtTokenProvider.refreshToken(tokenRequestDTO.getTokenAccess());
		return tokenRefreshed;
	}

	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	private AuthResponseDTO mapUserEntityToAuthResponseDTO(UserEntity userEntity) {
		AuthResponseDTO authResponseDTO = this.modelMapper.map(userEntity, AuthResponseDTO.class);
		return authResponseDTO;
	}

	private RoleResponseDTO mapRoleEntityToRoleResponseDTO(RoleEntity roleEntity) {
		RoleResponseDTO roleResponseDTO = this.modelMapper.map(roleEntity, RoleResponseDTO.class);
		return roleResponseDTO;
	}

	// -------------------------------------------------------------- jwt ------------------------------------------------------------- //
	private String getToken(AuthRequestDTO loginDTO) {
		Authentication authentication = this.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// obtenemos el token del jwtTokenProvider
		return this.jwtTokenProvider.generateToken(authentication);
	}

}