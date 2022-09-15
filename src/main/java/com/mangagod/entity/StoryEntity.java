package com.mangagod.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.mangagod.entity.base.BaseEntity;

@Entity
@Table(name = "stories") 
public class StoryEntity extends BaseEntity {
	
	@Column(name = "title", nullable = false, unique = true)
	private String title;
	@Column(name = "year", nullable = false)
	private String year;
	@Column(name = "synopsis", nullable = false)
	private String synopsis;
	@Column(name = "state", nullable = false)
	private Boolean state;
	@Column(name = "url_image")
	private String urlImage;
	@Column(name = "adaptation_anime", nullable = false)
	private Boolean adaptationAnime;
	@Column(name = "price")
	private Double price;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private CountryEntity country;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "demography_id")
	private DemographyEntity demography;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private CategoryEntity category;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "story")
	private Set<CharacterEntity> characters = new HashSet<>();
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "stories_genres", joinColumns = @JoinColumn(name = "story_id", referencedColumnName = "id"), 
									    inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
	private Set<GenreEntity> genres = new HashSet<>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "story", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<StoryMangakaEntity> storiesMangakas = new HashSet<>();
		
	public StoryEntity() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public Boolean getAdaptationAnime() {
		return adaptationAnime;
	}

	public void setAdaptationAnime(Boolean adaptationAnime) {
		this.adaptationAnime = adaptationAnime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity country) {
		this.country = country;
	}

	public DemographyEntity getDemography() {
		return demography;
	}

	public void setDemography(DemographyEntity demography) {
		this.demography = demography;
	}
	
	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public Set<CharacterEntity> getCharacters() {
		return characters;
	}

	public void setCharacters(Set<CharacterEntity> characters) {
		this.characters = characters;
	}

	public Set<GenreEntity> getGenres() {
		return genres;
	}

	public void setGenres(Set<GenreEntity> genres) {
		this.genres = genres;
	}

	public Set<StoryMangakaEntity> getStoriesMangakas() {
		return storiesMangakas;
	}

	public void setStoriesMangakas(Set<StoryMangakaEntity> storiesMangakas) {
		this.storiesMangakas = storiesMangakas;
	}
	
}