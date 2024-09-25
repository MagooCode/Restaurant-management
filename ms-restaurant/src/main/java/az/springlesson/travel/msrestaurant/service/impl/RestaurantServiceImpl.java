package az.springlesson.travel.msrestaurant.service.impl;

import az.springlesson.travel.msrestaurant.client.UserServiceClient;
import az.springlesson.travel.msrestaurant.dao.request.RestaurantRequest;
import az.springlesson.travel.msrestaurant.dao.request.ReviewRequest;
import az.springlesson.travel.msrestaurant.dao.response.RestaurantResponse;
import az.springlesson.travel.msrestaurant.entity.RestaurantEntity;
import az.springlesson.travel.msrestaurant.repository.RestaurantRepository;
import az.springlesson.travel.msrestaurant.repository.ReviewRepository;
import az.springlesson.travel.msrestaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.springlesson.travel.msrestaurant.mapper.RestaurantMapper.RESTAURANT_MAPPER;
import static az.springlesson.travel.msrestaurant.mapper.ReviewMapper.*;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final UserServiceClient userServiceClient;
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(UserServiceClient userServiceClient, ReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
        this.userServiceClient = userServiceClient;
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public void createReview(ReviewRequest reviewRequest, String username) {
        Long userId = userServiceClient.getIdByUsername(username);
        reviewRepository.save(REVIEW_MAPPER.mapToEntity(reviewRequest, userId));
    }

    @Override
    public void createRestaurant(RestaurantRequest restaurantRequest) {
        restaurantRepository.save(RESTAURANT_MAPPER.mapToEntity(restaurantRequest));
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantRepository.findAll().stream().map(RESTAURANT_MAPPER::mapToResponse).toList();
    }

    @Override
    public RestaurantResponse getRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).map(RESTAURANT_MAPPER::mapToResponse).orElse(null);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    @Override
    public void updateRestaurant(Long restaurantId, RestaurantRequest restaurantRequest) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        restaurant.setRestaurantId(restaurantId);
        restaurant.setRestaurantAddress(restaurantRequest.getRestaurantAddress());
        restaurant.setRestaurantName(restaurantRequest.getRestaurantName());
        restaurant.setDetails(restaurantRequest.getDetails());
        restaurant.setEmail(restaurantRequest.getEmail());
        restaurant.setPhoneNumber(restaurantRequest.getPhoneNumber());
        restaurant.setOwnerId(restaurantRequest.getOwnerId());

        restaurantRepository.save(restaurant);

    }


}
