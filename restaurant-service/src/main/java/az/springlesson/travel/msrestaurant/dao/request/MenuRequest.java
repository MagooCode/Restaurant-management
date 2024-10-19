package az.springlesson.travel.msrestaurant.dao.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MenuRequest {
    private List<Long> mealIds;
    private Long restaurantId;

}
