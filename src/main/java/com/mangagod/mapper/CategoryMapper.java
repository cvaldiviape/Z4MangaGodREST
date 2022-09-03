package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mangagod.dto.data.CategoryDataDTO;
import com.mangagod.dto.request.CategoryRequestDTO;
import com.mangagod.entity.CategoryEntity;;

@Component
public class CategoryMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public CategoryDataDTO mapEntityToDataDTO(CategoryEntity entity) {
		CategoryDataDTO dataDTO = this.modelMapper.map(entity, CategoryDataDTO.class);
		return dataDTO;
	}
	
	public CategoryEntity mapRequestToEntity(CategoryRequestDTO requestDTO) {
		CategoryEntity entity = this.modelMapper.map(requestDTO, CategoryEntity.class);
		return entity;
	}
	
}