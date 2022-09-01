package com.mangagod.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.mangagod.entity.base.BaseEntity;

@Entity
@Table(name = "genres") 
public class GenreEntity extends BaseEntity {

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "genres")
	private Set<StoryEntity> stories = new HashSet<>();
		
	public GenreEntity() {
		
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

	public Set<StoryEntity> getStories() {
		return stories;
	}

	public void setStories(Set<StoryEntity> stories) {
		this.stories = stories;
	}
	
}