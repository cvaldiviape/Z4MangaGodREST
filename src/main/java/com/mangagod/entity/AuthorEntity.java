package com.mangagod.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.mangagod.entity.base.BaseEntity;

@Entity
@Table(name = "authors") 
public class AuthorEntity extends BaseEntity {

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "artists")
	private Set<StoryEntity> stories = new HashSet<>();
	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name="mangaka_id")
	private MangakaEntity mangaka;
	
	public AuthorEntity() {
		
	}

	public Set<StoryEntity> getStories() {
		return stories;
	}

	public void setStories(Set<StoryEntity> stories) {
		this.stories = stories;
	}

	public MangakaEntity getMangaka() {
		return mangaka;
	}

	public void setMangaka(MangakaEntity mangaka) {
		this.mangaka = mangaka;
	}
	
}