package com.mangagod.dto.response;

public class MainResponse {

	public Boolean success;
	public String message;
	public Object data;
	
	public MainResponse() {
		
	}
	
	public MainResponse(Boolean success, String message, Object data) {
		this.success = success;
		this.message = message;
		this.data = data;	
	}
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
		
}