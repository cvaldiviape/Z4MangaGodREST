package com.mangagod.dto.data;

public class MangakasJobsDataDTO {

	private Integer mangakaId;
	private String nameMangaka;
	private Integer jobId;
	private String nameJob;
	
	public MangakasJobsDataDTO() {
		
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