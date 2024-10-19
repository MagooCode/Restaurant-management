package az.msrestaurantauth.client;

import az.msrestaurantauth.dao.UserRequest;
import az.msrestaurantauth.dao.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-RESTAURANT-USER")
public interface UserClient {
    @GetMapping("/api/v1/user/username")
    ResponseEntity<UserResponse> getUserByUsername(@RequestParam String username);

    @GetMapping("/api/v1/user/{email}/{username}")
    ResponseEntity<UserResponse> getUserByEmailOrUsername(@PathVariable String email, @PathVariable String username);

    @PostMapping("/api/v1/user/")
    ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest);
}
