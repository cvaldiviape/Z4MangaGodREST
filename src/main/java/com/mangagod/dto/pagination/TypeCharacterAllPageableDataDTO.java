package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.pagination.base.PageableDataDTOImpl;
import com.mangagod.dto.response.TypeCharacterResponseDTO;

public class TypeCharacterAllPageableDataDTO extends PageableDataDTOImpl {

	private List<TypeCharacterResponseDTO> typeCharacters;
	
	public TypeCharacterAllPageableDataDTO() {
		
	}

	public List<TypeCharacterResponseDTO> getTypeCharacters() {
		return typeCharacters;
	}

	public void setTypeCharacters(List<TypeCharacterResponseDTO> typeCharacters) {
		this.typeCharacters = typeCharacters;
	}

}