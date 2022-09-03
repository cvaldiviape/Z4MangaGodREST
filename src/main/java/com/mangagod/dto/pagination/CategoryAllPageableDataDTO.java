package com.mangagod.dto.pagination;

import java.util.List;
import com.mangagod.dto.data.CategoryDataDTO;
import com.mangagod.dto.pagination.base.PageableDataDTOImpl;

public class CategoryAllPageableDataDTO extends PageableDataDTOImpl {

	private List<CategoryDataDTO> categories;
	
	public CategoryAllPageableDataDTO() {
		
	}

	public List<CategoryDataDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDataDTO> categories) {
		this.categories = categories;
	}
	
}