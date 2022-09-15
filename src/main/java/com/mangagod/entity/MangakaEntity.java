package com.mangagod.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.mangagod.entity.base.BaseEntity;
import com.mangagod.util.enums.Sex;

@Entity
@Table(name = "mangakas")
public class MangakaEntity extends BaseEntity {

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@Enumerated(EnumType.STRING)
	@Column(name = "sex")
	private Sex sex;
	@Column(name = "birth_date")
	private LocalDate birthDate;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "mangaka", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<StoryMangakaEntity> storiesMangakas = new HashSet<>();
	
	public MangakaEntity() {
		
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

	public Set<StoryMangakaEntity> getStoriesMangakas() {
		return storiesMangakas;
	}

	public void setStoriesMangakas(Set<StoryMangakaEntity> storiesMangakas) {
		this.storiesMangakas = storiesMangakas;
	}
	
}