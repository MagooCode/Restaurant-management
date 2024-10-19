package az.msrestaurantauth.controller;

import az.msrestaurantauth.dao.*;
import az.msrestaurantauth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.generateToken(authRequest));
    }

    @PostMapping("register/user")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest){
        return ResponseEntity.ok(authService.registerUser(userRegistrationRequest));
    }

    @PostMapping("register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminRegistrationRequest adminRegistrationRequest){
        return ResponseEntity.ok(authService.registerAdmin(adminRegistrationRequest));
    }



}
