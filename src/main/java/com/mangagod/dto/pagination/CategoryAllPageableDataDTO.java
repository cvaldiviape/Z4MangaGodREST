package com.mangagod.dto.pagination;

import java.util.List;

import com.mangagod.dto.pagination.base.PageableDataDTOImpl;
import com.mangagod.dto.response.CategoryResponseDTO;

public class CategoryAllPageableDataDTO extends PageableDataDTOImpl {

	private List<CategoryResponseDTO> categories;
	
	public CategoryAllPageableDataDTO() {
		
	}

	public List<CategoryResponseDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryResponseDTO> categories) {
		this.categories = categories;
	}
	
}