package com.mangagod.dto.data;

import java.util.Set;

public class StoryDataDTO {

	private Integer Id;
	private String title;
	private String year;
	private String synopsis;
	private Boolean state;
	private String urlImage;
	private Boolean adaptationAnime;
	private Double price;
	private CountryDataDTO country;
	private DemographyDataDTO demography;
	private CategoryDataDTO category;
	private Set<GenreDataDTO> genres;
	private Set<MangakasJobsDataDTO> mangakasJobs;
	
    public StoryDataDTO() {
    	
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
	
	public CountryDataDTO getCountry() {
		return country;
	}
	
	public void setCountry(CountryDataDTO country) {
		this.country = country;
	}
	
	public DemographyDataDTO getDemography() {
		return demography;
	}
	
	public void setDemography(DemographyDataDTO demography) {
		this.demography = demography;
	}
	
	public CategoryDataDTO getCategory() {
		return category;
	}
	
	public void setCategory(CategoryDataDTO category) {
		this.category = category;
	}
	
	public Set<GenreDataDTO> getGenres() {
		return genres;
	}
	
	public void setGenres(Set<GenreDataDTO> genres) {
		this.genres = genres;
	}

	public Set<MangakasJobsDataDTO> getMangakasJobs() {
		return mangakasJobs;
	}

	public void setMangakasJobs(Set<MangakasJobsDataDTO> mangakasJobs) {
		this.mangakasJobs = mangakasJobs;
	}

}