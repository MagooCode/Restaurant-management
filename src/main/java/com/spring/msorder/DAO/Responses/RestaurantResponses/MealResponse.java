package com.spring.msorder.DAO.Responses.RestaurantResponses;

import com.spring.msorder.DAO.Responses.DetailsResponses.DetailsResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MealResponse {
    private Long id;
    private String mealName;
    private Float mealPrice;
    private String category;
    private String imageUrl;
    private DetailsResponse details;
}
