package com.mangagod.dto.response;

public class StoriesJobsResponseDTO {

	private Integer storyId;
	private String nameStory;
	private Integer jobId;
	private String nameJob;
	
	public StoriesJobsResponseDTO() {
		
	}
	
	public Integer getStoryId() {
		return storyId;
	}

	public void setStoryId(Integer storyId) {
		this.storyId = storyId;
	}

	public String getNameStory() {
		return nameStory;
	}

	public void setNameStory(String nameStory) {
		this.nameStory = nameStory;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getNameJob() {
		return nameJob;
	}

	public void setNameJob(String nameJob) {
		this.nameJob = nameJob;
	}
	
}