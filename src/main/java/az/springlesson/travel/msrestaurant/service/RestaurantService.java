package az.springlesson.travel.msrestaurant.service;

import az.springlesson.travel.msrestaurant.dao.request.RestaurantRequest;
import az.springlesson.travel.msrestaurant.dao.request.ReviewRequest;
import az.springlesson.travel.msrestaurant.dao.response.RestaurantResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
    void createReview(ReviewRequest reviewRequest, String username);
    void createRestaurant(RestaurantRequest restaurantRequest);

    List<RestaurantResponse> getAllRestaurants();

    RestaurantResponse getRestaurantById(Long restaurantId);

    void deleteRestaurant(Long restaurantId);

    void updateRestaurant(Long restaurantId, RestaurantRequest restaurantRequest);
}
