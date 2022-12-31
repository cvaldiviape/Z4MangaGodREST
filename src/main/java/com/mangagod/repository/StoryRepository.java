package com.mangagod.repository;

import java.util.Optional;
import com.mangagod.entity.StoryEntity;
import com.mangagod.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.EntityGraph;

public interface StoryRepository extends BaseRepository<StoryEntity, Integer> {

	public Optional<StoryEntity> findByTitle(String name);
	
	public Boolean existsByTitle(String name);

	@EntityGraph(value = "StoryEntity.multiples-relationships") // para ver la diferencia sin con "@EntityGraph", debo comentar esta linea.
    public StoryEntity findByPrice(Double price);

}