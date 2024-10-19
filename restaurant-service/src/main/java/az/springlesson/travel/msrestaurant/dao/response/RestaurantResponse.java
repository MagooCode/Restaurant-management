package az.springlesson.travel.msrestaurant.dao.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantResponse {
    private Long restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String description;
    private String phoneNumber;
    private String email;
    private Float restaurantRating;
    private Long ownerId;
}
