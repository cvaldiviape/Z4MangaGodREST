package com.mangagod.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class StoryJobRequestDTO {

	@NotNull(message = "El campo 'storyId' no debe ser nulo.")
	@Min(value = 1, message = "El campo 'storyId' debe contener un número mayor a '0'")
	private Integer storyId;
	@NotNull(message = "El campo 'jobId' no debe ser nulo.")
	@Min(value = 1, message = "El campo 'jobId' debe contener un número mayor a '0'")
	private Integer jobId;
	
	public StoryJobRequestDTO() {
		
	}
		
	public Integer getStoryId() {
		return storyId;
	}

	public void setStoryId(Integer storyId) {
		this.storyId = storyId;
	}



	public Integer getJobId() {
		return jobId;
	}
	
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	
}