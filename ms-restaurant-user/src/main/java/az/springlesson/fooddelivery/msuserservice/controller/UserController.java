package az.springlesson.fooddelivery.msuserservice.controller;

import az.springlesson.fooddelivery.msuserservice.dao.request.UserRequest;
import az.springlesson.fooddelivery.msuserservice.dao.response.UserResponse;
import az.springlesson.fooddelivery.msuserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/id/{username}")
    public ResponseEntity<Long> getIdByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getIdByUsername(username));
    }

    @GetMapping("username")
    public ResponseEntity<UserResponse> getUserByUsername(@RequestParam String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("{email}/{username}")
    public ResponseEntity<UserResponse> getUserByEmailOrUsername(@PathVariable String email, @PathVariable String username){
        return ResponseEntity.ok(userService.getUserByEmailOrUsername(email,username));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.create(userRequest));
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
