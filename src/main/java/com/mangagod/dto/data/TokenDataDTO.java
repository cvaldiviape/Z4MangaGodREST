package com.mangagod.dto.data;

public class TokenDataDTO {

	private String tokenAccess;
	private String tokenType;
	private Integer tokenExpiredIn;
	
	public TokenDataDTO() {
		
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