package az.springlesson.travel.msrestaurant.controller;

import az.springlesson.travel.msrestaurant.dao.request.RestaurantRequest;
import az.springlesson.travel.msrestaurant.dao.request.ReviewRequest;
import az.springlesson.travel.msrestaurant.dao.response.RestaurantResponse;
import az.springlesson.travel.msrestaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/restaurant/")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping()
    public void createRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        restaurantService.createRestaurant(restaurantRequest);
    }

    @PostMapping("/review")
    public void createReview(@RequestBody ReviewRequest reviewRequest, @RequestHeader String username){
        restaurantService.createReview(reviewRequest,username);
    }

    @GetMapping()
    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("{restaurantId}")
    public RestaurantResponse getRestaurantById(@PathVariable Long restaurantId) {
        return restaurantService.getRestaurantById(restaurantId);
    }

    @DeleteMapping("{restaurantId}")
    public void deleteRestaurant(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
    }

    @PutMapping("{restaurantId}")
    public void updateRestaurant(@PathVariable Long restaurantId, @RequestBody RestaurantRequest restaurantRequest) {
        restaurantService.updateRestaurant(restaurantId,restaurantRequest);
    }

}
