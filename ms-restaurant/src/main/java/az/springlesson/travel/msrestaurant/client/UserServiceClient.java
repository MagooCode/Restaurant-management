package az.springlesson.travel.msrestaurant.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "MS-USER"
)
public interface UserServiceClient {
    @GetMapping("id")
    Long getIdByUsername(@RequestParam String username);

}
