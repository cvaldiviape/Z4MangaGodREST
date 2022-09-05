package com.mangagod.dto.pagination;

import java.util.List;
import com.mangagod.dto.data.MangakaDataDTO;
import com.mangagod.dto.pagination.base.PageableDataDTOImpl;

public class MangakaAllPageableDataDTO extends PageableDataDTOImpl {

	private List<MangakaDataDTO> mangakas;
	
	public MangakaAllPageableDataDTO() {
		
	}

	public List<MangakaDataDTO> getMangakas() {
		return mangakas;
	}

	public void setMangakas(List<MangakaDataDTO> mangakas) {
		this.mangakas = mangakas;
	}

}