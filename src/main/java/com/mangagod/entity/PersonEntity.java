package com.mangagod.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.mangagod.entity.base.BaseEntity;

@Entity
@Table(name = "persons")
public class PersonEntity extends BaseEntity {

	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "persons")
	private Set<StoryEntity> stories = new HashSet<>();
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "persons_professions", joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"), 
									         inverseJoinColumns = @JoinColumn(name = "profession_id", referencedColumnName = "id"))
	private Set<ProfessionEntity> professions = new HashSet<>();
	
	public PersonEntity() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	public Set<ProfessionEntity> getProfessions() {
		return professions;
	}
	
	public void setProfessions(Set<ProfessionEntity> professions) {
		this.professions = professions;
	}
	
}