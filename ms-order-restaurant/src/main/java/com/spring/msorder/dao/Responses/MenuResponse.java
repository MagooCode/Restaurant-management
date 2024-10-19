package com.spring.msorder.dao.Responses;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.msorder.dao.Responses.RestaurantResponses.MealResponse;
import com.spring.msorder.dao.Responses.RestaurantResponses.RestaurantResponse;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Builder
@Data
public class MenuResponse {
    private Long id;
    private List<MealResponse> meals;
    private RestaurantResponse restaurant;
}
