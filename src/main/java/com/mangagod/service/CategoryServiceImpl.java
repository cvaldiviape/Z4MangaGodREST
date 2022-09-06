package com.mangagod.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mangagod.dto.data.CategoryDataDTO;
import com.mangagod.dto.pagination.CategoryAllPageableDataDTO;
import com.mangagod.dto.request.CategoryRequestDTO;
import com.mangagod.entity.CategoryEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.CategoryMapper;
import com.mangagod.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryMapper categoryMapper;
		
	// ----------------------------------------------------------- services ----------------------------------------------------------- //
	@Override
	public CategoryAllPageableDataDTO getAll(Integer numberPage, Integer sizePage, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending();
	
		// agregando paginación
		Pageable pageable = PageRequest.of(numberPage, sizePage, sort);
		Page<CategoryEntity> categoriesPageable = this.categoryRepository.findAll(pageable);	
		List<CategoryEntity> categoriesEntity = categoriesPageable.getContent();
		List<CategoryDataDTO> categoriesDTO = categoriesEntity.stream().map((x) -> this.categoryMapper.mapEntityToDataDTO(x)).collect(Collectors.toList());	
		
		CategoryAllPageableDataDTO pageableDataDTO = new CategoryAllPageableDataDTO();
		pageableDataDTO.setCategories(categoriesDTO);
		pageableDataDTO.setNumberPage(categoriesPageable.getNumber());
		pageableDataDTO.setSizePage(categoriesPageable.getSize());
		pageableDataDTO.setTotalElements(categoriesPageable.getTotalElements());
		pageableDataDTO.setTotalPages(categoriesPageable.getTotalPages());
		pageableDataDTO.setIsLastPage(categoriesPageable.isLast());
		
		return pageableDataDTO;
	}

	@Override
	public CategoryDataDTO getById(Integer id) {
		// TODO Auto-generated method stub
		CategoryEntity entity = this.categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id));
		CategoryDataDTO dataDTO = this.categoryMapper.mapEntityToDataDTO(entity);
		return dataDTO;
	}

	@Override
	public CategoryDataDTO create(CategoryRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		Boolean existNname = this.categoryRepository.existsByName(requestDTO.getName());
		if(existNname) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		CategoryEntity entity = this.categoryMapper.mapRequestToEntity(requestDTO);
		
		CategoryDataDTO dataCreated = this.categoryMapper.mapEntityToDataDTO(this.categoryRepository.save(entity));			
		return dataCreated;
	}

	@Override
	public CategoryDataDTO update(Integer id, CategoryRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		CategoryEntity dataCurrent = this.categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id));
		Boolean existsName = this.categoryRepository.existsByName(requestDTO.getName());
		Boolean diferentNameCurrent = (!requestDTO.getName().equalsIgnoreCase(dataCurrent.getName()));
		if(existsName && diferentNameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		dataCurrent.setName(requestDTO.getName());
		
		CategoryDataDTO dataUpdated = this.categoryMapper.mapEntityToDataDTO(this.categoryRepository.save(dataCurrent));	
		return dataUpdated;
	}

	@Override
	public CategoryDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		CategoryEntity entity = this.categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		this.categoryRepository.delete(entity);
		CategoryDataDTO dataDeleted = this.categoryMapper.mapEntityToDataDTO(entity);
		return dataDeleted;
	}

}