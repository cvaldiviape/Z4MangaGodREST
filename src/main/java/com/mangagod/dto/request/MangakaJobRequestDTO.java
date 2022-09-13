package com.mangagod.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MangakaJobRequestDTO {

	@NotNull(message = "El campo 'mangakaId' no debe ser nulo.")
	@Min(value = 1, message = "El campo 'mangakaId' debe contener un número mayor a '0'")
	private Integer mangakaId;
	@NotNull(message = "El campo 'jobId' no debe ser nulo.")
	@Min(value = 1, message = "El campo 'jobId' debe contener un número mayor a '0'")
	private Integer jobId;
	
	public MangakaJobRequestDTO() {
		
	}
	
	public Integer getMangakaId() {
		return mangakaId;
	}
	
	public void setMangakaId(Integer mangakaId) {
		this.mangakaId = mangakaId;
	}
	
	public Integer getJobId() {
		return jobId;
	}
	
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	
}