package com.mangagod.security;

public class JWTAuthResonseDTO {

	private String tokenAccess;
	private String tokenType = "Bearer";

	public JWTAuthResonseDTO(String tokenAccess) {
		super();
		this.tokenAccess = tokenAccess;
	}

	public JWTAuthResonseDTO(String tokenAccess, String tokenType) {
		super();
		this.tokenAccess = tokenAccess;
		this.tokenType = tokenType;
	}

	public String getTokenDeAcceso() {
		return tokenAccess;
	}

	public void setTokenDeAcceso(String tokenAccess) {
		this.tokenAccess = tokenAccess;
	}

	public String getTipoDeToken() {
		return tokenType;
	}

	public void setTipoDeToken(String tokenType) {
		this.tokenType = tokenType;
	}

}
