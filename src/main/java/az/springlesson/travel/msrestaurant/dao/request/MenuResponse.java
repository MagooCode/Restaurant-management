package az.springlesson.travel.msrestaurant.dao.request;

import az.springlesson.travel.msrestaurant.dao.response.MealResponse;
import az.springlesson.travel.msrestaurant.dao.response.RestaurantResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MenuResponse {
    private Long id;
    private List<MealResponse> meals;
    private RestaurantResponse restaurant;
}
