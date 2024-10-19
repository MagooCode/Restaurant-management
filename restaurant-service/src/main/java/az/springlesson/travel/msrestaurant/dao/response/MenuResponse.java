package az.springlesson.travel.msrestaurant.dao.response;

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
