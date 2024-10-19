package com.spring.msorder.client;

import com.spring.msorder.dao.Responses.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MS-RESTAURANT-USER")
public interface UserClient {
    @GetMapping("/api/v1/user/username")
    ResponseEntity<UserResponse> getUserByUsername(@RequestParam String username);
}
