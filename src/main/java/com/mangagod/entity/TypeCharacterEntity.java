package com.mangagod.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.mangagod.entity.base.BaseEntity;

@Entity
@Table(name = "types_characters") 
public class TypeCharacterEntity extends BaseEntity {

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "type") 
	private Set<CharacterEntity> characters = new HashSet<>();
	
	public TypeCharacterEntity() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CharacterEntity> getCharacters() {
		return characters;
	}

	public void setCharacters(Set<CharacterEntity> characters) {
		this.characters = characters;
	}
	
}