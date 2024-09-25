package az.springlesson.travel.msrestaurant.dao.response;

import az.springlesson.travel.msrestaurant.entity.Details;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantResponse {
    private Long restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private DetailsResponse details;
    private String phoneNumber;
    private String email;
    private Float restaurantRating;
}
