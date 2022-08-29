package com.mangagod.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mangagod.entity.ProfessionEntity;

public interface ProfessionRepository extends JpaRepository<ProfessionEntity, Integer> {

	public Optional<ProfessionEntity> findByName(String name);
	
	public Boolean existsByName(String name);
	
}