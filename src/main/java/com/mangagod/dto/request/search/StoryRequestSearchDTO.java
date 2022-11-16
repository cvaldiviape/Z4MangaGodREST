package com.mangagod.dto.request.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StoryRequestSearchDTO {

    public String title;
    public String synopsis;
    public Boolean state;
    public List<Boolean> states;
    public Integer idCategory;
    public List<Integer> idCategories;
    public Integer idCountry;
    public List<Integer> idCountries;
    public String genre;

}