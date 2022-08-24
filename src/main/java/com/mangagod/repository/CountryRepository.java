package com.mangagod.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mangagod.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {

	public Optional<CountryEntity> findByName(String name);
	
	public Boolean existsByName(String name);
	
}