package az.springlesson.travel.msrestaurant.client;

import az.springlesson.travel.msrestaurant.dao.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "MS-RESTAURANT-USER"
)
public interface UserServiceClient {
    @GetMapping("/api/v1/user/id/{username}")
    ResponseEntity<Long> getIdByUsername(@PathVariable String username);

    @GetMapping("/api/v1/user/{id}")
    ResponseEntity<UserResponse> getUserById(@PathVariable Long id);

    @GetMapping("/api/v1/user/username")
    ResponseEntity<UserResponse> getUserByUsername(@RequestParam String username);


}
