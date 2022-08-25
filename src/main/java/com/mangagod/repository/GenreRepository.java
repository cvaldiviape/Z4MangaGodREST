package com.mangagod.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mangagod.entity.GenreEntity;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer>{

	public Optional<GenreEntity> findByName(String name);
	
	public Boolean existsByName(String name);
	
}