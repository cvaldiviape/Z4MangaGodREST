package com.mangagod.dto.response;

public class TokenResponseDTO {

	private String tokenAccess;
	private String tokenType;
	private Integer tokenExpiredIn;
	
	public TokenResponseDTO() {
		
	}
	
	public TokenResponseDTO(String tokenAccess, String tokenType, Integer tokenExpiredIn) {
		super();
		this.tokenAccess = tokenAccess;
		this.tokenType = tokenType;
		this.tokenExpiredIn = tokenExpiredIn;
	}

	public String getTokenAccess() {
		return tokenAccess;
	}

	public void setTokenAccess(String tokenAccess) {
		this.tokenAccess = tokenAccess;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Integer getTokenExpiredIn() {
		return tokenExpiredIn;
	}

	public void setTokenExpiredIn(Integer tokenExpiredIn) {
		this.tokenExpiredIn = tokenExpiredIn;
	}
	
}