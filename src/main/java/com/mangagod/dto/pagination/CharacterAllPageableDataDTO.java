package com.mangagod.dto.pagination;

import java.util.List;
import com.mangagod.dto.data.CharacterDataDTO;
import com.mangagod.dto.pagination.base.PageableDataDTOImpl;

public class CharacterAllPageableDataDTO extends PageableDataDTOImpl {

	private List<CharacterDataDTO> characters;
	
	public CharacterAllPageableDataDTO() {
		
	}

	public List<CharacterDataDTO> getCharacters() {
		return characters;
	}

	public void setCharacters(List<CharacterDataDTO> characters) {
		this.characters = characters;
	}
	
}