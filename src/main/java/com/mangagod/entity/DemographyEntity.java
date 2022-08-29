package com.mangagod.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mangagod.entity.base.BaseEntity;

@Entity
@Table(name = "demographies") 
public class DemographyEntity extends BaseEntity {

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "demographic")
	private Set<MangaEntity> mangas = new HashSet<>();
	
	public DemographyEntity() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<MangaEntity> getMangas() {
		return mangas;
	}

	public void setMangas(Set<MangaEntity> mangas) {
		this.mangas = mangas;
	}
	
}