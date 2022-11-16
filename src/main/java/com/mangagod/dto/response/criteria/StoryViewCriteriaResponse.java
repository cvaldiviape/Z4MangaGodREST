package com.mangagod.dto.response.criteria;

import com.mangagod.dto.response.CategoryResponseDTO;
import com.mangagod.dto.response.CountryResponseDTO;
import com.mangagod.dto.response.DemographyResponseDTO;
import java.io.Serial;
import java.io.Serializable;

public class StoryViewCriteriaResponse  implements Serializable {

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
