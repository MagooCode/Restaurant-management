package az.springlesson.travel.msrestaurant.controller;

import az.springlesson.travel.msrestaurant.dao.request.RestaurantRequest;
import az.springlesson.travel.msrestaurant.dao.request.ReviewRequest;
import az.springlesson.travel.msrestaurant.dao.response.RestaurantResponse;
import az.springlesson.travel.msrestaurant.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createRestaurant(@Valid @RequestBody RestaurantRequest restaurantRequest) {
       return ResponseEntity.ok(restaurantService.createRestaurant(restaurantRequest));
    }

    @PostMapping("/review")
    public ResponseEntity<String> createReview(@Valid @RequestBody ReviewRequest reviewRequest, @RequestHeader String username){
        return ResponseEntity.ok(restaurantService.createReview(reviewRequest,username));
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
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.deleteRestaurant(restaurantId));
    }

    @PutMapping("{restaurantId}")
    public ResponseEntity<String> updateRestaurant(@PathVariable Long restaurantId, @Valid @RequestBody RestaurantRequest restaurantRequest) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantId,restaurantRequest));
    }

}
