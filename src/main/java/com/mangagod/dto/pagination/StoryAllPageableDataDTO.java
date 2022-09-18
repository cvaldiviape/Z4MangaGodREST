package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.pagination.base.PageableDataDTOImpl;
import com.mangagod.dto.response.StoryResponseDTO;

public class StoryAllPageableDataDTO extends PageableDataDTOImpl{

	private List<StoryResponseDTO> stories;
	
	public StoryAllPageableDataDTO() {
		
	}

	public List<StoryResponseDTO> getStories() {
		return stories;
	}

	public void setStories(List<StoryResponseDTO> stories) {
		this.stories = stories;
	}

}