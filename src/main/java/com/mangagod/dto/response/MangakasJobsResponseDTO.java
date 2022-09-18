package com.mangagod.dto.response;

public class MangakasJobsResponseDTO {

	private Integer mangakaId;
	private String nameMangaka;
	private Integer jobId;
	private String nameJob;
	
	public MangakasJobsResponseDTO() {
		
	}

	public Integer getMangakaId() {
		return mangakaId;
	}

	public void setMangakaId(Integer mangakaId) {
		this.mangakaId = mangakaId;
	}

	public String getNameMangaka() {
		return nameMangaka;
	}

	public void setNameMangaka(String nameMangaka) {
		this.nameMangaka = nameMangaka;
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