package com.mangagod.dto.request;

import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class StoryRequestDTO {

	@NotNull(message = "El campo 'title' no debe ser nulo.")
	@NotBlank(message = "El campo 'title' es obligatorio.")
	@Size( max = 50, message = "El campo 'title' debe contener maximo de 50 caracteres.")
	@Pattern(regexp= "^[a-zA-ZÀ-ÿ0-9¿?,.!¡:\\-\\#$\\(\\)]+(\\s?[a-zA-ZÀ-ÿ0-9¿?,.!¡:\\-\\#$\\(\\)]+?)+$", message = "El campo 'title' no admite caracteres extraños." )
	private String title;
	@NotNull(message = "El campo 'year' no debe ser nulo.")
	@NotBlank(message = "El campo 'year' es obligatorio.")
	private String year;
	@NotNull(message = "El campo 'synopsis' no debe ser nulo.")
	@NotBlank(message = "El campo 'synopsis' es obligatorio.")
	private String synopsis;
	@NotNull(message = "El campo 'state' no debe ser nulo.")
	private Boolean state;
	@NotNull(message = "El campo 'urlImage' no debe ser nulo.")
	@NotBlank(message = "El campo 'urlImage' es obligatorio.")
	@Pattern(regexp= "^(ftp|http|https):\\/\\/[^ \"]+$", message = "El campo 'urlImage' debe ser una url valida.")
	private String urlImage;
	@NotNull(message = "El campo 'adaptationAnime' no debe ser nulo.")
	private Boolean adaptationAnime;
	private Double price;
	@NotNull(message = "El campo 'countryId' no debe ser nulo.")
	@Min(value = 1, message = "El campo 'countryId' debe contener un número mayor a '0'")
	private Integer countryId;
	@NotNull(message = "El campo 'demographyId' no debe ser nulo.")
	@Min(value = 1, message = "El campo 'demographyId' debe contener un número mayor a '0'")
	private Integer demographyId;
	@NotNull(message = "El campo 'categoryId' no debe ser nulo.")
	@Min(value = 1, message = "El campo 'categoryId' debe contener un número mayor a '0'")
	private Integer categoryId;
	@NotNull(message = "El campo 'genreIds' es obligatorio.")
	@NotEmpty(message = "El campo 'genreIds' debe contene al menos 1 elemento.")
	private Set<Integer> genreIds;
//	@NotNull(message = "El campo 'mangakaJobIds' no debe ser nulo.")
//	@NotEmpty(message = "El campo 'mangakaJobIds' debe contene al menos 1 elemento.")
	private Set<MangakaJobRequestDTO> mangakaJobIds;
	
    public StoryRequestDTO() {
    	
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
	
	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getDemographyId() {
		return demographyId;
	}

	public void setDemographyId(Integer demographyId) {
		this.demographyId = demographyId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Set<Integer> getGenreIds() {
		return genreIds;
	}

	public void setGenreIds(Set<Integer> genreIds) {
		this.genreIds = genreIds;
	}

	public Set<MangakaJobRequestDTO> getMangakaJobIds() {
		return mangakaJobIds;
	}

	public void setMangakaJobIds(Set<MangakaJobRequestDTO> mangakaJobIds) {
		this.mangakaJobIds = mangakaJobIds;
	}

}