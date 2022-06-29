package com.mangagod.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mangas") 
public class MangaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
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
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "country_id")
	private CountryEntity country;
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "demographic_id")
	private DemographyEntity demographic;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manga", cascade = {CascadeType.REMOVE})
	private Set<CharacterEntity> characters = new HashSet<>();
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "mangas_genres", joinColumns = @JoinColumn(name = "manga_id", referencedColumnName = "id"), 
									   inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
	private Set<GenreEntity> genres = new HashSet<>();
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "mangas_personss", joinColumns = @JoinColumn(name = "manga_id", referencedColumnName = "id"), 
									   inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
	private Set<PersonEntity> persons = new HashSet<>();
	
	public MangaEntity() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity country) {
		this.country = country;
	}

	public DemographyEntity getDemographic() {
		return demographic;
	}

	public void setDemographic(DemographyEntity demographic) {
		this.demographic = demographic;
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

	public Set<PersonEntity> getPersons() {
		return persons;
	}

	public void setPersons(Set<PersonEntity> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return "MangaEntity [id=" + id + ", title=" + title + ", year=" + year + ", synopsis=" + synopsis + ", state="
				+ state + ", urlImage=" + urlImage + ", adaptationAnime=" + adaptationAnime + ", price=" + price
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", country=" + country + ", demographic="
				+ demographic + ", characters=" + characters + ", genres=" + genres + ", persons=" + persons + "]";
	}
	
}
