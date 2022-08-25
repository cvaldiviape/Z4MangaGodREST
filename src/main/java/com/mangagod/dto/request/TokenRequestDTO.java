package com.mangagod.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TokenRequestDTO {

	@NotNull(message = "El campo 'tokenAccess' es obligatorio.")
	@NotBlank(message = "El campo 'tokenAccess' es obligatorio.")
	private String tokenAccess;
	
	public TokenRequestDTO() {
		
	}

	public String getTokenAccess() {
		return tokenAccess;
	}

	public void setTokenAccess(String tokenAccess) {
		this.tokenAccess = tokenAccess;
	}
		
}