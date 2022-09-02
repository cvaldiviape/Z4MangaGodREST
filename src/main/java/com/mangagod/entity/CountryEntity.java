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
@Table(name = "countries") 
public class CountryEntity extends BaseEntity {

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	private Set<StoryEntity> stories = new HashSet<>();
	
	public CountryEntity() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<StoryEntity> getStories() {
		return stories;
	}

	public void setStories(Set<StoryEntity> stories) {
		this.stories = stories;
	}

}