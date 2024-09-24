package az.springlesson.fooddelivery.msuserservice.controller;

import az.springlesson.fooddelivery.msuserservice.dao.Request.userRequest;
import az.springlesson.fooddelivery.msuserservice.dao.Response.userResponse;
import az.springlesson.fooddelivery.msuserservice.model.User;
import az.springlesson.fooddelivery.msuserservice.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final userService userService;

    @Autowired
    public UserController(userService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<userResponse> getAllUsers() {
        return userService.getList();
    }

    @GetMapping("/{id}")
    public userResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody userRequest userRequest) {
        return userService.create(userRequest);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody userRequest userRequest) {
        userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
