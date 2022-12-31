package com.mangagod.repository.entitygraph;

import com.mangagod.dto.criteria.response.StorySomeFieldsResponseDTO;
import com.mangagod.dto.entitygraph.response.StoryTestResponseDTO;
import com.mangagod.entity.*;
import com.mangagod.mapper.StoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class StoryRepositoryGraphImpl implements StoryRepositoryGraph {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private StoryMapper storyMapper;

    @Override
    public List<StorySomeFieldsResponseDTO> testEntityGrahpCriteria() {

        // TODO - MAPEANDO LOS CAMPOS A MOSTRAR DE MI QUERY
//        EntityGraph entityGraph = this.em.createEntityGraph("test-mi-melek");
//        entityGraph.addAttributeNodes("id");
//        entityGraph.addAttributeNodes("title");
//        entityGraph.addAttributeNodes("year");
//
//        CriteriaBuilder builder = this.em.getCriteriaBuilder();
//
//        CriteriaQuery<StorySomeFieldsResponseDTO> queryConfig = builder.createQuery(StorySomeFieldsResponseDTO.class);
//        Root<StoryEntity> rootStory =  queryConfig.from(StoryEntity.class);
//
//        TypedQuery<StorySomeFieldsResponseDTO> typedQuery = this.em.createQuery(queryConfig);
//        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);
//        List<StorySomeFieldsResponseDTO> listStories = typedQuery.getResultList();

        //---------------------------------------------------------------------------------------------------//


        EntityGraph entityGraphXXX = this.em.getEntityGraph("StoryEntity.country");
        CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();

        CriteriaQuery<StoryEntity> queryConfig = criteriaBuilder.createQuery(StoryEntity.class);
        Root<StoryEntity> rootStory = queryConfig.from(StoryEntity.class);

        TypedQuery<StoryEntity> typedQueryxxxxxx = this.em.createQuery(queryConfig);
        typedQueryxxxxxx.setHint("javax.persistence.fetchgraph", entityGraphXXX);
        List<StoryEntity> listStories = typedQueryxxxxxx.getResultList();

        return null;
    }

    @Override
    public StoryTestResponseDTO findByPriceCriteria(Double price) {

        EntityGraph<StoryEntity> eg = this.em.createEntityGraph(StoryEntity.class);
        eg.addAttributeNodes("country");
        eg.addAttributeNodes("category");
//        eg.addAttributeNodes("storiesMangakas");
        eg.addSubgraph("storiesMangakas").addAttributeNodes("mangaka");

//        EntityGraph<?> eg = this.em.getEntityGraph("StoryEntity.country");

        CriteriaBuilder builder = this.em.getCriteriaBuilder();

        CriteriaQuery<StoryEntity> queryConfig = builder.createQuery(StoryEntity.class);
        Root<StoryEntity> rootStory = queryConfig.from(StoryEntity.class);

        queryConfig.where(builder.equal(rootStory.get("price"), price));

        TypedQuery<StoryEntity> typedQuery = this.em.createQuery(queryConfig);
        typedQuery.setHint("javax.persistence.fetchgraph", eg);
        StoryEntity story = typedQuery.getSingleResult();

        return StoryTestResponseDTO.builder()
                .id(story.getId())
                .title(story.getTitle())
                .year(story.getYear())
                .price(story.getPrice())
                .build();
    }

}