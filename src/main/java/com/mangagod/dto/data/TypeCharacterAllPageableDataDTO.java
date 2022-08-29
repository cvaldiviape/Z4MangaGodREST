package com.mangagod.dto.data;

import java.util.List;
import com.mangagod.dto.data.pagination.PageableDataDTOImpl;

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