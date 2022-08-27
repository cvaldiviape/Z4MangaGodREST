package com.mangagod.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mangagod.entity.DemographyEntity;

public interface DemographyRepository extends JpaRepository<DemographyEntity, Integer> {

	public Optional<DemographyEntity> findByName(String name);
	
	public Boolean existsByName(String name);

}