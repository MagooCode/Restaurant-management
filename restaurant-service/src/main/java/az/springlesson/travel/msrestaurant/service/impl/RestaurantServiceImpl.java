package az.springlesson.travel.msrestaurant.service.impl;

import az.springlesson.travel.msrestaurant.client.UserServiceClient;
import az.springlesson.travel.msrestaurant.dao.request.RestaurantRequest;
import az.springlesson.travel.msrestaurant.dao.request.ReviewRequest;
import az.springlesson.travel.msrestaurant.dao.response.RestaurantResponse;
import az.springlesson.travel.msrestaurant.dao.response.UserResponse;
import az.springlesson.travel.msrestaurant.entity.RestaurantEntity;
import az.springlesson.travel.msrestaurant.repository.RestaurantRepository;
import az.springlesson.travel.msrestaurant.repository.ReviewRepository;
import az.springlesson.travel.msrestaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.springlesson.travel.msrestaurant.enums.InfoMessage.*;
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

    public String createReview(ReviewRequest reviewRequest, String username) {
        Long userId = userServiceClient.getIdByUsername(username).getBody();
        reviewRepository.save(REVIEW_MAPPER.mapToEntity(reviewRequest, userId));
        return REVIEW_CREATED.getMessage();
    }

    @Override
    public String createRestaurant(RestaurantRequest restaurantRequest) {
        UserResponse userResponse = userServiceClient.getUserById(restaurantRequest.getOwnerId()).getBody();

        restaurantRepository.save(RESTAURANT_MAPPER.mapToEntity(restaurantRequest));

        return RESTAURANT_CREATED.getMessage();
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
    public String deleteRestaurant(Long restaurantId) {

        restaurantRepository.deleteById(restaurantId);
        return RESTAURANT_DELETED.getMessage();
    }

    @Override
    public String updateRestaurant(Long restaurantId, RestaurantRequest restaurantRequest) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        restaurant.setRestaurantId(restaurantId);
        restaurant.setRestaurantAddress(restaurantRequest.getRestaurantAddress());
        restaurant.setRestaurantName(restaurantRequest.getRestaurantName());
        restaurant.setDescription(restaurantRequest.getDescription());
        restaurant.setEmail(restaurantRequest.getEmail());
        restaurant.setPhoneNumber(restaurantRequest.getPhoneNumber());
        restaurant.setOwnerId(restaurantRequest.getOwnerId());

        restaurantRepository.save(restaurant);

        return RESTAURANT_UPDATED.getMessage();

    }


}
