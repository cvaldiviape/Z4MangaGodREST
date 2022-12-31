package com.mangagod.dto.entitygraph.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StoryTestResponseDTO {

    private Integer id;
    private String title;
    private Short year;
    private Double price;

    public StoryTestResponseDTO(Integer id, String title, Short year, Double price) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.price = price;
    }

}