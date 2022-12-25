package com.mangagod.dto.criteria.response;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class StoryGenresManyToManyResponseDTO {

    private Integer id;
    private String title;
    private Short year;
    private String synopsis;
    private String genre;

    public StoryGenresManyToManyResponseDTO(Integer id, String title, Short year, String synopsis, String genre) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.synopsis = synopsis;
        this.genre = genre;
    }

    @Data
    public static class StoryViewCriteriaResponse implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;
        private Integer Id;
        private String title;
        private Short year;
        private String synopsis;
        private Boolean state;
        private String urlImage;
        private Boolean adaptationAnime;
        private Double price;

        private String country;
        private String demography;
        private String category;

    }
}