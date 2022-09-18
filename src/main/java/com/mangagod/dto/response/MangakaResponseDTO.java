package com.mangagod.dto.response;

import java.time.LocalDate;
import java.util.Set;

import com.mangagod.util.enums.Sex;

public class MangakaResponseDTO {

	private Integer id;
	private String name;
	private Sex sex;
	private LocalDate birthDate;
	private Set<StoriesJobsResponseDTO> storiesJobs;
	
	public MangakaResponseDTO() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Set<StoriesJobsResponseDTO> getStoriesJobs() {
		return storiesJobs;
	}

	public void setStoriesJobs(Set<StoriesJobsResponseDTO> storiesJobs) {
		this.storiesJobs = storiesJobs;
	}
		
}