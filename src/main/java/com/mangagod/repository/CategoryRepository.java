package com.mangagod.repository;

import java.util.Optional;
import com.mangagod.entity.CategoryEntity;
import com.mangagod.repository.base.BaseRepository;

public interface CategoryRepository extends BaseRepository<CategoryEntity,Integer> {

	public Optional<CategoryEntity> findByName(String name);
	
	public Boolean existsByName(String name);

	default public Optional<CategoryEntity> findCategory(Integer id)  {
		System.out.println("Call repository to get id=" + id);
		return this.findById(id);
	}
	
}