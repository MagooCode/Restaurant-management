package az.springlesson.travel.msrestaurant.dao.response;

import az.springlesson.travel.msrestaurant.entity.Details;
import az.springlesson.travel.msrestaurant.enums.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MealResponse {
    private Long id;
    private String mealName;
    private Float mealPrice;
    private String category;
    private String imageUrl;
    private Details details;
}
