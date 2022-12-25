package com.mangagod.dto.criteria.response;

import lombok.Data;

@Data
public class StorySomeFieldsResponseDTO {

    private Integer id;
    private String title;
    private Short year;

    public StorySomeFieldsResponseDTO(Integer id, String title, Short year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }
}