package com.mangagod.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mangagod.entity.TypeCharacterEntity;

public interface TypeCharacter extends JpaRepository<TypeCharacterEntity, Integer> {

	public Optional<TypeCharacterEntity> findByName(String name);
	
	public Boolean existsByName(String name);
	
}