package com.mangagod.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mangagod.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

	public Optional<RoleEntity> findByName(String name);
	
	public Boolean existsByName(String name);
	
}
