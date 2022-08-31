package com.mangagod.repository;

import java.util.Optional;
import com.mangagod.entity.ProfessionEntity;
import com.mangagod.repository.base.BaseRepository;

public interface ProfessionRepository extends BaseRepository<ProfessionEntity, Integer> {

	public Optional<ProfessionEntity> findByName(String name);
	
	public Boolean existsByName(String name);
	
}