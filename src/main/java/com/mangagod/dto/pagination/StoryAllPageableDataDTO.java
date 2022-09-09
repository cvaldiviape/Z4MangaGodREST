package com.mangagod.dto.pagination;

import java.util.List;
import com.mangagod.dto.data.StoryDataDTO;
import com.mangagod.dto.pagination.base.PageableDataDTOImpl;

public class StoryAllPageableDataDTO extends PageableDataDTOImpl{

	private List<StoryDataDTO> stories;
	
	public StoryAllPageableDataDTO() {
		
	}

	public List<StoryDataDTO> getStories() {
		return stories;
	}

	public void setStories(List<StoryDataDTO> stories) {
		this.stories = stories;
	}

}