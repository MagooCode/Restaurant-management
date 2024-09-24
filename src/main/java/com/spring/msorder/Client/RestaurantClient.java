package com.spring.msorder.Client;

import com.spring.msorder.DAO.Responses.RestaurantResponses.MealResponse;
import com.spring.msorder.DAO.Responses.RestaurantResponses.RestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MS-RESTAURANT")
public interface RestaurantClient {
    @GetMapping("api/v1/restaurant/{restaurantId}")
    public RestaurantResponse getRestaurantById(@PathVariable Long restaurantId);

    @GetMapping("api/v1/meal/{id}")
    public ResponseEntity<MealResponse> getMealById(@PathVariable Long id);
}
