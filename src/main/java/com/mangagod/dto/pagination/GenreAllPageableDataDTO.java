package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.data.GenreDataDTO;
import com.mangagod.dto.pagination.base.PageableDataDTOImpl;

public class GenreAllPageableDataDTO extends PageableDataDTOImpl {

	private List<GenreDataDTO> genres;
	
	public GenreAllPageableDataDTO() {
		
	}

	public List<GenreDataDTO> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreDataDTO> genres) {
		this.genres = genres;
	}
	
}