package com.mangagod.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "types_characters") 
public class TypeCharacterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "type") 
	private Set<CharacterEntity> characters = new HashSet<>();
	
	public TypeCharacterEntity() {
		
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

	public Set<CharacterEntity> getCharacters() {
		return characters;
	}

	public void setCharacters(Set<CharacterEntity> characters) {
		this.characters = characters;
	}

	@Override
	public String toString() {
		return "TypeCharacterEntity [id=" + id + ", name=" + name + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", characters=" + characters + "]";
	}
	
}
