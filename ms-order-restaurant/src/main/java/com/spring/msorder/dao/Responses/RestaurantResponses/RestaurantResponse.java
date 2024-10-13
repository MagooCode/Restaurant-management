package com.spring.msorder.dao.Responses.RestaurantResponses;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RestaurantResponse {
    private Long restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String description;
    private String phoneNumber;
    private String email;
    private Float restaurantRating;
}
