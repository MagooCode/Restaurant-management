package az.springlesson.travel.msrestaurant.mapper;

import az.springlesson.travel.msrestaurant.dao.request.RestaurantRequest;
import az.springlesson.travel.msrestaurant.dao.response.RestaurantResponse;
import az.springlesson.travel.msrestaurant.entity.RestaurantEntity;

public enum RestaurantMapper {
    RESTAURANT_MAPPER;

    public RestaurantEntity mapToEntity(RestaurantRequest restaurantRequest) {
        return RestaurantEntity.builder()
                .restaurantName(restaurantRequest.getRestaurantName())
                .restaurantAddress(restaurantRequest.getRestaurantAddress())
                .description(restaurantRequest.getDescription())
                .phoneNumber(restaurantRequest.getPhoneNumber())
                .ownerId(restaurantRequest.getOwnerId())
                .email(restaurantRequest.getEmail()).build();

    }

    public RestaurantResponse mapToResponse(RestaurantEntity restaurantEntity) {
        return RestaurantResponse.builder()
                .restaurantId(restaurantEntity.getRestaurantId())
                .restaurantName(restaurantEntity.getRestaurantName())
                .restaurantRating(restaurantEntity.getRestaurantRating())
                .restaurantAddress(restaurantEntity.getRestaurantAddress())
                .phoneNumber(restaurantEntity.getPhoneNumber())
                .email(restaurantEntity.getEmail())
                .ownerId(restaurantEntity.getOwnerId())
                .description(restaurantEntity.getDescription())
                .build();
    }
}
