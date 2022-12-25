package com.mangagod.repository.criteria;

import com.mangagod.dto.criteria.request.StoryByCountryRequestDTO;
import com.mangagod.dto.criteria.request.StoryByGenreRequestDTO;
import com.mangagod.dto.criteria.request.StoryByMangakaRequestDTO;
import com.mangagod.dto.criteria.response.StoryJoinResponseDTO;
import com.mangagod.dto.criteria.request.StoryTitleRequestDTO;
import com.mangagod.dto.criteria.response.StoryGenresManyToManyResponseDTO;
import com.mangagod.dto.criteria.response.StoryMangakasResponseDTO;
import com.mangagod.dto.criteria.response.StorySomeFieldsResponseDTO;
import com.mangagod.dto.response.StoryResponseDTO;
import com.mangagod.entity.*;
import com.mangagod.mapper.StoryMapper;
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
public class StoryRepositoryCriteriaImpl implements StoryRepositoryCriteria {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private StoryMapper storyMapper;

    @Override
    public List<StoryResponseDTO> findAllSimple() {
        // PASO 1 - crear una fabriba de elementos de consulta
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        // TODO: EJM-1: consulta a la tabla "stories"
        // PASO 2 - configurar la consulta - indicado la estructura de la consulta, es decir, los campos a mostrar
        CriteriaQuery<StoryEntity> queryConfig1 = builder.createQuery(StoryEntity.class);
        Root<StoryEntity> rootStory1 =  queryConfig1.from(StoryEntity.class); // establezco en punto de partida, es decir, si quisiera a partir de la entidad "Story" puede enlazar mediante joins a otras tablas.
        queryConfig1.select(rootStory1);
        // PASO 3 - ejecucion de la consuta con base a la configuracion
        TypedQuery<StoryEntity> typedQuery1 = this.em.createQuery(queryConfig1);
        List<StoryEntity> listStories = typedQuery1.getResultList();

        return listStories.stream().map((x) -> this.storyMapper.mapEntityToResponseDTO(x)).toList();
    }

    @Override
    public List<StorySomeFieldsResponseDTO> findAllByOnlySomeFields() {
        // PASO 1 - crear una fabriba de elementos de consulta
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        // TODO: EJM-2: consulta a la tabla "stories", pero solo mostrar el campo "title"
        // PASO 2 - configurar la consulta - indicado la estructura de la consulta, es decir, los campos a mostrar
        CriteriaQuery<StorySomeFieldsResponseDTO> queryConfig = builder.createQuery(StorySomeFieldsResponseDTO.class);
        Root<StoryEntity> rootStory =  queryConfig.from(StoryEntity.class);

        queryConfig.multiselect(
                rootStory.get("id"),
                rootStory.get("title"),
                rootStory.get("year")
        );

        // PASO 3 - ejecucion de la consuta con base a la configuracion
        TypedQuery<StorySomeFieldsResponseDTO> typedQuery = this.em.createQuery(queryConfig);
        List<StorySomeFieldsResponseDTO> listStories = typedQuery.getResultList();
        return listStories;
    }

    @Override
    public List<StorySomeFieldsResponseDTO> findAllByTitle(StoryTitleRequestDTO dto) {
        // PASO 1 - crear una fabriba de elementos de consulta
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        // TODO: EJM-3: consulta a la tabla "stories", buscar por campo "title"
        // PASO 2 - configurar la consulta - indicado la estructura de la consulta, es decir, los campos a mostrar
        CriteriaQuery<StorySomeFieldsResponseDTO> queryConfig = builder.createQuery(StorySomeFieldsResponseDTO.class);
        Root<StoryEntity> rootStory =  queryConfig.from(StoryEntity.class);

        queryConfig.multiselect( // seteando los campos deacuerdo a las propiedades de StorySomeFieldsResponseDTO
                rootStory.get("id"),
                rootStory.get("title"),
                rootStory.get("year")
        );

        queryConfig.where(builder.equal(rootStory.get("title"), dto.getTitle()));


        // PASO 3 - ejecucion de la consuta con base a la configuracion
        TypedQuery<StorySomeFieldsResponseDTO> typedQuery3 = this.em.createQuery(queryConfig);
        List<StorySomeFieldsResponseDTO> listStories = typedQuery3.getResultList();

        return listStories;
    }

    @Override
    public List<StorySomeFieldsResponseDTO> findAllByTitleOrderByYear(StoryTitleRequestDTO dto) {
        // PASO 1 - crear una fabriba de elementos de consulta
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        // TODO: EJM-4: consulta a la tabla "stories", buscar por campo "synopsis" y ordenar de forma descendente por "title"
        // PASO 2 - configurar la consulta - indicado la estructura de la consulta, es decir, los campos a mostrar
        CriteriaQuery<StorySomeFieldsResponseDTO> queryConfig = builder.createQuery(StorySomeFieldsResponseDTO.class);
        Root<StoryEntity> rootStory =  queryConfig.from(StoryEntity.class);

        queryConfig.multiselect( // seteando los campos deacuerdo a las propiedades de StorySomeFieldsResponseDTO
                rootStory.get("id"),
                rootStory.get("title"),
                rootStory.get("year")
        );

        queryConfig.where(builder.equal(rootStory.get("title"), dto.getTitle())); // buscar por campo Title
        queryConfig.orderBy(builder.desc(rootStory.get("year"))); // ordenar por campo Year

        // PASO 3 - ejecucion de la consuta con base a la configuracion
        TypedQuery<StorySomeFieldsResponseDTO> typedQuery4 = this.em.createQuery(queryConfig);
        List<StorySomeFieldsResponseDTO> listStories = typedQuery4.getResultList();

        return listStories;
    }

    @Override
    public List<StoryJoinResponseDTO> findAllWithJoin(StoryByCountryRequestDTO dto) {
        // PASO 1 - crear una fabriba de elementos de consulta
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        // TODO: EJM-5: consulta a la tabla "stories", ordenada de forma descendente por el campo "name" que pertenece la relacion con la tabla "countries"
        // PASO 2 - configurar la consulta - indicado la estructura de la consulta, es decir, los campos a mostrar
        CriteriaQuery<StoryJoinResponseDTO> queryConfig = builder.createQuery(StoryJoinResponseDTO.class);
        Root<StoryEntity> rootStory =  queryConfig.from(StoryEntity.class);

        // JOIN
        Join<StoryEntity, CountryEntity> countryJoin = rootStory.join("country"); // este "country" hace referencia al campo de "StoryEntity" que apunta a la relacion con la tabla "CountryEntity"
        Join<StoryEntity, DemographyEntity> demographyJoin = rootStory.join("demography"); // este "demography" hace referencia al campo de "StoryEntity" que apunta a la relacion con la tabla "DemographyEntity"
        Join<StoryEntity, CategoryEntity> categoryJoin = rootStory.join("category"); // este "category" hace referencia al campo de "StoryEntity" que apunta a la relacion con la tabla "CategoryEntity"

        queryConfig.multiselect(
                rootStory.get("id"),
                rootStory.get("title"),
                rootStory.get("year"),
                rootStory.get("synopsis"),
                countryJoin.get("name"),
                demographyJoin.get("name"),
                categoryJoin.get("name")
        );

        // FORMA 1: consultar a travez de una tabla relacionada, en este caso consultando la "story" en base a su relacion con "country"
        queryConfig.where(builder.equal(rootStory.get("country").get("name"), dto.getCountryName())); // Alemania:4, Italia:112, Brasil:33
        // FORMA 2: consultar a travez de una tabla relacionada, en este caso consultando la "story" en base a su relacion con "country"
        // queryConfig.where(builder.equal(countryJoin.get("name"), dto.getCountryName())); // Alemania:4, Italia:112, Brasil:33

        queryConfig.orderBy(builder.desc(rootStory.get("demography").get("name"))); // ordenando  descendete por name demography

        // PASO 3 - ejecucion de la consuta con base a la configuracion
        TypedQuery<StoryJoinResponseDTO> typedQuery = this.em.createQuery(queryConfig);
        List<StoryJoinResponseDTO> listStories = typedQuery.getResultList();

        return listStories;
    }

    @Override
    public List<StoryGenresManyToManyResponseDTO> findAllWithJoinGenres(StoryByGenreRequestDTO dto) {
        // PASO 1 - crear una fabriba de elementos de consulta
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        // TODO: EJM-6: consulta a la tabla "stories", JOIN
        // PASO 2 - configurar la consulta - indicado la estructura de la consulta, es decir, los campos a mostrar
        CriteriaQuery<StoryGenresManyToManyResponseDTO> queryConfig = builder.createQuery(StoryGenresManyToManyResponseDTO.class);
        Root<StoryEntity> rootStory =  queryConfig.from(StoryEntity.class);

        // JOIN - Many to Many: aparentemente el "JOIN" lo relaciona ya sea "1:N" o "N:M"
        Join<StoryEntity, GenreEntity> joinGenres = rootStory.join("genres");// este "genres" hace referencia al campo de "StoryEntity" que apunta a la relacion con la tabla "Genre"

        queryConfig.multiselect(
                rootStory.get("id"),
                rootStory.get("title"),
                rootStory.get("year"),
                rootStory.get("synopsis"),
                joinGenres.get("name")
        );

        // queryConfig.distinct(true); // Util cuando no quiero registros repetidos
        queryConfig.where(builder.equal(joinGenres.get("name"), dto.getGenreName()));

        // PASO 3 - ejecucion de la consuta con base a la configuracion
        TypedQuery<StoryGenresManyToManyResponseDTO> typedQuery = this.em.createQuery(queryConfig);
        List<StoryGenresManyToManyResponseDTO> stories = typedQuery.getResultList();
        return stories;
    }

    @Override
    public List<StoryMangakasResponseDTO> findAllWithJoinMangaka(StoryByMangakaRequestDTO dto) {
        // PASO 1 - crear una fabriba de elementos de consulta
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        // TODO: EJM-6: consulta a la tabla "stories", JOIN
        // PASO 2 - configurar la consulta - indicado la estructura de la consulta, es decir, los campos a mostrar
        CriteriaQuery<StoryMangakasResponseDTO> queryConfig = builder.createQuery(StoryMangakasResponseDTO.class);
        Root<StoryEntity> rootStory =  queryConfig.from(StoryEntity.class);

        // JOIN - relacionando una tabla detras de otra
        Join<StoryEntity, StoryMangakaEntity> joinStoryMangaka = rootStory.join("storiesMangakas"); // "storiesMangakas" hace referencia al campo de "StoryEntity" que apunta a la relacion con "StorMangakaEntity"
        Join<StoryMangakaEntity, MangakaEntity> joinMangaka = joinStoryMangaka.join("mangaka"); // "mangaka" hace referencia al campo de "StoryMangakaEntity" que apunta a la relacion con "MangakaEntity"

        queryConfig.multiselect(
                rootStory.get("id"),
                rootStory.get("title"),
                rootStory.get("year"),
                rootStory.get("synopsis"),
                joinMangaka.get("name")
        );

        queryConfig.where(builder.equal(joinMangaka.get("name"), dto.getMangakaName()));

        // PASO 3 - ejecucion de la consuta con base a la configuracion
        TypedQuery<StoryMangakasResponseDTO> typedQuery = this.em.createQuery(queryConfig);
        List<StoryMangakasResponseDTO> stories = typedQuery.getResultList();
        return stories;
    }

}