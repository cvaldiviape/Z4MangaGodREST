package com.mangagod.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.mangagod.entity.base.BaseEntity;

@NoRepositoryBean // indico que de esta interface no se puedan crear instancias
public interface BaseRepository <Entity extends BaseEntity, ID> extends JpaRepository<Entity, ID>{

}