package az.msrestaurantauth.service;

import az.msrestaurantauth.dao.*;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    JwtResponse generateToken(AuthRequest authRequest);

    String registerUser(UserRegistrationRequest userRegistrationRequest);

    String registerAdmin(AdminRegistrationRequest adminRegistrationRequest);
}
