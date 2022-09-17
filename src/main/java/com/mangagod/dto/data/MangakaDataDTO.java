package com.mangagod.dto.data;

import java.time.LocalDate;
import java.util.Set;

import com.mangagod.util.enums.Sex;

public class MangakaDataDTO {

	private Integer id;
	private String name;
	private Sex sex;
	private LocalDate birthDate;
	private Set<StoriesJobsDataDTO> storiesJobs;
	
	public MangakaDataDTO() {
		
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

	public Set<StoriesJobsDataDTO> getStoriesJobs() {
		return storiesJobs;
	}

	public void setStoriesJobs(Set<StoriesJobsDataDTO> storiesJobs) {
		this.storiesJobs = storiesJobs;
	}
		
}