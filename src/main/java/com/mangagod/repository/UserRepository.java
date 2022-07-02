package com.mangagod.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mangagod.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	public Optional<UserEntity> findByEmail(String email);
	
	public Optional<UserEntity> findByUsernameOrEmail(String username, String email);
	
	public Optional<UserEntity> findByUsername(String username);
	
	public Boolean existsByUsername(String username);
	
	public Boolean existsByEmail(String email);
	
}
