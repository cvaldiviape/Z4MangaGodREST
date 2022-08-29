package com.mangagod.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ProfessionCreateRequestDTO {

	@NotNull(message = "El campo 'name' es obligatorio.")
	@NotBlank(message = "El campo 'name' es obligatorio.")
	@Size(max = 50, message = "El campo 'name' debe contener un maximo de 50 caracteres.")
	@Pattern(regexp= "^[a-zA-ZÀ-ÿ]+(\s?[a-zA-ZÀ-ÿ]+?)+$", message = "El campo 'name' solo admite letras." )
	private String name;

	public ProfessionCreateRequestDTO() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
