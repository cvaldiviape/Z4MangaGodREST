package com.mangagod.dto.criteria.response;

import lombok.Data;

@Data
public class StoryMangakasResponseDTO {

    private Integer id;
    private String title;
    private Short year;
    private String synopsis;
    private String mangaka;

    public StoryMangakasResponseDTO(Integer id, String title, Short year, String synopsis, String mangaka) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.synopsis = synopsis;
        this.mangaka = mangaka;
    }

}