package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.pagination.base.PageableDataDTOImpl;
import com.mangagod.dto.response.CharacterResponseDTO;

public class CharacterAllPageableDataDTO extends PageableDataDTOImpl {

	private List<CharacterResponseDTO> characters;
	
	public CharacterAllPageableDataDTO() {
		
	}

	public List<CharacterResponseDTO> getCharacters() {
		return characters;
	}

	public void setCharacters(List<CharacterResponseDTO> characters) {
		this.characters = characters;
	}
	
}