package com.spring.msorder.dao.Responses.RestaurantResponses;

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
    private String description;
}
