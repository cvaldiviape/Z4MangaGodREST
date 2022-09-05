package com.mangagod.entity;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import com.mangagod.util.ids.StoryMangakaId;

@Entity
@Table(name = "stories_mangakas") 
public class StoryMangaka {

	@EmbeddedId
	private StoryMangakaId id = new StoryMangakaId();
	@ManyToOne
	@MapsId("storyId") // este es el nombre de attributo en la clase "StoryMangakaId"
	@JoinColumn(name = "story_id")
	private StoryEntity story;
	@ManyToOne
	@MapsId("mangakaId") // este es el nombre de attributo en la clase "StoryMangakaId"
	@JoinColumn(name = "mangaka_id")
	private MangakaEntity mangaka;
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "job_id")
	private JobEntity job;
	
	
	public StoryMangaka() {
		
	}


	public StoryMangakaId getId() {
		return id;
	}


	public void setId(StoryMangakaId id) {
		this.id = id;
	}


	public StoryEntity getStory() {
		return story;
	}


	public void setStory(StoryEntity story) {
		this.story = story;
	}


	public MangakaEntity getMangaka() {
		return mangaka;
	}


	public void setMangaka(MangakaEntity mangaka) {
		this.mangaka = mangaka;
	}


	public JobEntity getJob() {
		return job;
	}

	public void setJob(JobEntity job) {
		this.job = job;
	}
	
}