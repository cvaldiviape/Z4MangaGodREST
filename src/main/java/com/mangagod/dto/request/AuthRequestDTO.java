package com.mangagod.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AuthRequestDTO {

	@NotNull(message = "El campo 'usernameOrEmail' es obligatorio.")
	@NotBlank(message = "El campo 'usernameOrEmail' es obligatorio.")
	private String usernameOrEmail;
	@NotNull(message = "El campo 'password' es obligatorio.")
	@NotBlank(message = "El campo 'password' es obligatorio.")
	private String password;
	@NotNull(message = "El campo 'roleId' es obligatorio.")
	@Min(value = 1, message = "El campo 'roleId' debe contener un número mayor a '0'")
	private Integer roleId;

	public AuthRequestDTO() {

	}

	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
}