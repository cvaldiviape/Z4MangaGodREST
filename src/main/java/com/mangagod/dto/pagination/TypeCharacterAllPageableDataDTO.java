package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.data.TypeCharacterDataDTO;
import com.mangagod.dto.pagination.base.PageableDataDTOImpl;

public class TypeCharacterAllPageableDataDTO extends PageableDataDTOImpl {

	private List<TypeCharacterDataDTO> typeCharacters;
	
	public TypeCharacterAllPageableDataDTO() {
		
	}

	public List<TypeCharacterDataDTO> getTypeCharacters() {
		return typeCharacters;
	}

	public void setTypeCharacters(List<TypeCharacterDataDTO> typeCharacters) {
		this.typeCharacters = typeCharacters;
	}

}