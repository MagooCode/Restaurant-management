package com.spring.msorder.DAO.Responses.RestaurantResponses;

import com.spring.msorder.DAO.Responses.DetailsResponses.DetailsResponse;
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
    private DetailsResponse details;
    private String phoneNumber;
    private String email;
    private Float restaurantRating;
}
