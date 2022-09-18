package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.pagination.base.PageableDataDTOImpl;
import com.mangagod.dto.response.MangakaResponseDTO;

public class MangakaAllPageableDataDTO extends PageableDataDTOImpl {

	private List<MangakaResponseDTO> mangakas;
	
	public MangakaAllPageableDataDTO() {
		
	}

	public List<MangakaResponseDTO> getMangakas() {
		return mangakas;
	}

	public void setMangakas(List<MangakaResponseDTO> mangakas) {
		this.mangakas = mangakas;
	}

}