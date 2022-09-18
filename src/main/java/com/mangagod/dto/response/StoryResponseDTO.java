package com.mangagod.dto.response;

import java.util.Set;

public class StoryResponseDTO {

	private Integer Id;
	private String title;
	private String year;
	private String synopsis;
	private Boolean state;
	private String urlImage;
	private Boolean adaptationAnime;
	private Double price;
	private CountryResponseDTO country;
	private DemographyResponseDTO demography;
	private CategoryResponseDTO category;
	private Set<GenreResponseDTO> genres;
	private Set<MangakasJobsResponseDTO> mangakasJobs;
	
    public StoryResponseDTO() {
    	
    }
    
	public Integer getId() {
		return Id;
	}
	
	public void setId(Integer id) {
		Id = id;
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
	
	public CountryResponseDTO getCountry() {
		return country;
	}
	
	public void setCountry(CountryResponseDTO country) {
		this.country = country;
	}
	
	public DemographyResponseDTO getDemography() {
		return demography;
	}
	
	public void setDemography(DemographyResponseDTO demography) {
		this.demography = demography;
	}
	
	public CategoryResponseDTO getCategory() {
		return category;
	}
	
	public void setCategory(CategoryResponseDTO category) {
		this.category = category;
	}
	
	public Set<GenreResponseDTO> getGenres() {
		return genres;
	}
	
	public void setGenres(Set<GenreResponseDTO> genres) {
		this.genres = genres;
	}

	public Set<MangakasJobsResponseDTO> getMangakasJobs() {
		return mangakasJobs;
	}

	public void setMangakasJobs(Set<MangakasJobsResponseDTO> mangakasJobs) {
		this.mangakasJobs = mangakasJobs;
	}

}