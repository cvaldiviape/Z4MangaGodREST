package com.mangagod.repository.custom;

import com.mangagod.dto.request.search.StoryRequestSearchDTO;
import com.mangagod.dto.response.StoryResponseDTO;
import com.mangagod.dto.response.criteria.StoryViewCriteriaResponse;
import com.mangagod.entity.StoryEntity;
import com.mangagod.util.AppSession;
import com.mangagod.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class StoryRepositoryCustomImpl implements StoryRepositoryCustom {

//    @PersistenceContext(unitName = "entityManagerFactoryBean")
//    private EntityManager em;
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<StoryViewCriteriaResponse> findAllBySearch(StoryRequestSearchDTO dto) {
        // PASO 1 - crear una facriba de elementos de consulta
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        // TODO: EJM-1: consulta a la tabla "stories"
        // PASO 2 - configurar la consulta - indicado la estructura de la consulta, es decir, los campos a mostrar
        CriteriaQuery<StoryEntity> queryConfig1 = builder.createQuery(StoryEntity.class);
        Root<StoryEntity> rootStory1 =  queryConfig1.from(StoryEntity.class); // establezco en punto de partida, es decir, si quisiera a partir de la entidad "Story" puede enlazar mediante joins a otras tablas.
        queryConfig1.select(rootStory1);
        // PASO 3 - ejecucion de la consuta con base en la configuracion
        TypedQuery<StoryEntity> typedQuery1 = this.em.createQuery(queryConfig1);
        List<StoryEntity> result1 = typedQuery1.getResultList();

        // TODO: EJM-2: consulta a la tabla "stories", pero solo mostrar el campo "title"
        // PASO 2 - configurar la consulta - indicado la estructura de la consulta, es decir, los campos a mostrar
        CriteriaQuery<String> queryConfig2 = builder.createQuery(String.class);
        Root<StoryEntity> rootStory2 =  queryConfig2.from(StoryEntity.class);
        queryConfig2.select(rootStory2.get("title"));
        // PASO 3 - ejecucion de la consuta con base en la configuracion
        TypedQuery<String> typedQuery2 = this.em.createQuery(queryConfig2);
        List<String> result2 = typedQuery2.getResultList();

        // TODO: EJM-3: consulta a la tabla "stories", buscar por campo "title"
        // PASO 2 - configurar la consulta - indicado la estructura de la consulta, es decir, los campos a mostrar
        CriteriaQuery<StoryEntity> queryConfig3 = builder.createQuery(StoryEntity.class);
        Root<StoryEntity> rootStory3 =  queryConfig3.from(StoryEntity.class);
        queryConfig3.select(rootStory3);
        queryConfig3.where(builder.equal(rootStory3.get("title"), dto.getTitle()));
        // PASO 3 - ejecucion de la consuta con base en la configuracion
        TypedQuery<StoryEntity> typedQuery3 = this.em.createQuery(queryConfig3);
        List<StoryEntity> result3 = typedQuery3.getResultList();

        // TODO: EJM-4: consulta a la tabla "stories", buscar por campo "synopsis" y ordenar de forma descendente por "title"
        // PASO 2 - configurar la consulta - indicado la estructura de la consulta, es decir, los campos a mostrar
        CriteriaQuery<StoryEntity> queryConfig4 = builder.createQuery(StoryEntity.class);
        Root<StoryEntity> rootStory4 =  queryConfig4.from(StoryEntity.class);
        queryConfig4.select(rootStory4);
        queryConfig4.where(builder.equal(rootStory4.get("synopsis"), dto.getSynopsis()));
        queryConfig4.orderBy(builder.desc(rootStory4.get("title")));
        // PASO 3 - ejecucion de la consuta con base en la configuracion
        TypedQuery<StoryEntity> typedQuery4 = this.em.createQuery(queryConfig4);
        List<StoryEntity> result4 = typedQuery4.getResultList();

        // TODO: EJM-5: consulta a la tabla "stories", ordenada de forma descendente por el campo "name" que pertenece la relacion con la tabla "countries"
        // PASO 2 - configurar la consulta - indicado la estructura de la consulta, es decir, los campos a mostrar
        CriteriaQuery<StoryEntity> queryConfig5 = builder.createQuery(StoryEntity.class);
        Root<StoryEntity> rootStory5 =  queryConfig5.from(StoryEntity.class);
        queryConfig5.select(rootStory5);
        queryConfig5.orderBy(builder.desc(rootStory5.get("country").get("name")));
        // PASO 3 - ejecucion de la consuta con base en la configuracion
        TypedQuery<StoryEntity> typedQuery5 = this.em.createQuery(queryConfig5);
        List<StoryEntity> result5 = typedQuery5.getResultList();

        // TODO: EJM-5: consulta a la tabla "stories", JOIN
        // PASO 2 - configurar la consulta - indicado la estructura de la consulta, es decir, los campos a mostrar
        CriteriaQuery<StoryEntity> queryConfig6 = builder.createQuery(StoryEntity.class);
        Root<StoryEntity> rootStory6 =  queryConfig6.from(StoryEntity.class);
        Join<Object, Object> joinGenres = rootStory6.join("genres");// este "genres" hace referencia al campo de "stories" que apunta a la relacion con la tabla "genres"
        queryConfig6.select(rootStory6)
                .distinct(true)
                .where(builder.equal(joinGenres.get("name"), dto.getGenre()))
                .orderBy(builder.desc(rootStory6.get("title")));
        // PASO 3 - ejecucion de la consuta con base en la configuracion
        TypedQuery<StoryEntity> typedQuery6 = this.em.createQuery(queryConfig6);
        List<StoryEntity> result6 = typedQuery6.getResultList();

        return null;

    }

}