package com.mangagod.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.mangagod.entity.base.BaseEntity;

@Entity
@Table(name = "professions")
public class ProfessionEntity extends BaseEntity {

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@Column(name = "created_at", nullable = false)
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "professions")
	private Set<PersonEntity> persons = new HashSet<>();
	
	public ProfessionEntity() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PersonEntity> getPersons() {
		return persons;
	}

	public void setPersons(Set<PersonEntity> persons) {
		this.persons = persons;
	}

}