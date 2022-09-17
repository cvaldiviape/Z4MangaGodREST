package com.mangagod.dto.request;

import java.time.LocalDate;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import com.mangagod.util.enums.Sex;
//import com.mangagod.util.validators.EnumNamePattern;
import com.mangagod.util.validators.EnumValidator;
import io.swagger.annotations.ApiModelProperty;

public class MangakaRequestDTO {

	@NotNull(message = "El campo 'name' es obligatorio.")
	@NotBlank(message = "El campo 'name' es obligatorio.")
	@Size(max = 50, message = "El campo 'name' debe contener un maximo de 50 caracteres.")
	@Pattern(regexp= "^[a-zA-ZÀ-ÿ]+(\s?[a-zA-ZÀ-ÿ]+?)+$", message = "El campo 'name' solo admite letras." )
	private String name;
	// @EnumNamePattern(regexp = "NEW|DEFAULT")
	@ApiModelProperty("FEMENINO|MASCULINO|NO_ESPECIFICADO")
	@EnumValidator(enumClass = Sex.class, message = "valores aceptados -> FEMENINO|MASCULINO|NO_ESPECIFICADO")
	private String sex;
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy") // not working, REV
	private LocalDate birthDate;
	@NotNull(message = "El campo 'storyJobIds' no debe ser nulo.")
	@NotEmpty(message = "El campo 'storyJobIds' debe contene al menos 1 elemento.")
	private Set<StoryJobRequestDTO> storyJobIds;
	
	public MangakaRequestDTO() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Set<StoryJobRequestDTO> getStoryJobIds() {
		return storyJobIds;
	}

	public void setStoryJobIds(Set<StoryJobRequestDTO> storyJobIds) {
		this.storyJobIds = storyJobIds;
	}

}