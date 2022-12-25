package com.mangagod.dto.criteria.response;

import lombok.Data;

@Data
public class StoryJoinResponseDTO {

    private Integer id;
    private String title;
    private Short year;
    private String synopsis;
    private String country;
    private String demography;
    private String category;

    public StoryJoinResponseDTO(Integer id, String title, Short year, String synopsis, String country, String demography, String category) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.synopsis = synopsis;
        this.country = country;
        this.demography = demography;
        this.category = category;
    }

}