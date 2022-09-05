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
@Table(name = "jobs")
public class JobEntity  extends BaseEntity {

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "job")
	private Set<StoryMangaka> storiesMangakas = new HashSet<>();
	
	public JobEntity() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<StoryMangaka> getStoriesMangakas() {
		return storiesMangakas;
	}

	public void setStoriesMangakas(Set<StoryMangaka> storiesMangakas) {
		this.storiesMangakas = storiesMangakas;
	}
	
}