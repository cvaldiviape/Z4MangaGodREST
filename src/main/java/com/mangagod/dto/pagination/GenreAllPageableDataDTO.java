package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.pagination.base.PageableDataDTOImpl;
import com.mangagod.dto.response.GenreResponseDTO;

public class GenreAllPageableDataDTO extends PageableDataDTOImpl {

	private List<GenreResponseDTO> genres;
	
	public GenreAllPageableDataDTO() {
		
	}

	public List<GenreResponseDTO> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreResponseDTO> genres) {
		this.genres = genres;
	}
	
}