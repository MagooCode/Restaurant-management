package com.spring.msorder.client;

import com.spring.msorder.dao.Responses.MenuResponse;
import com.spring.msorder.dao.Responses.RestaurantResponses.MealResponse;
import com.spring.msorder.dao.Responses.RestaurantResponses.RestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MS-RESTAURANT")
public interface RestaurantClient {
    @GetMapping("api/v1/restaurant/{restaurantId}")
    RestaurantResponse getRestaurantById(@PathVariable Long restaurantId);

    @GetMapping("api/v1/meal/{id}")
    ResponseEntity<MealResponse> getMealById(@PathVariable Long id);

    @GetMapping("/api/v1/menu/{id}")
    MenuResponse getMenusById(@PathVariable Long id);
}
