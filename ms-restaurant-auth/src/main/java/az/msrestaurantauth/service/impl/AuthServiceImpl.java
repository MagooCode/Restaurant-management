package az.msrestaurantauth.service.impl;

import az.msrestaurantauth.client.UserClient;
import az.msrestaurantauth.dao.*;
import az.msrestaurantauth.exception.AlreadyExistsException;
import az.msrestaurantauth.exception.UnauthorizedException;
import az.msrestaurantauth.security.JwtUtil;
import az.msrestaurantauth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static az.msrestaurantauth.mapper.UserRequestMapper.USER_REQUEST_MAPPER;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserClient userClient;

    @Value("${admin.registration.code}")
    private String adminRegistrationCode;

    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserClient userClient) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userClient = userClient;
    }

    @Override
    public JwtResponse generateToken(AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()){
            String token = jwtUtil.generateToken(authentication);
            return JwtResponse.builder().token(token).build();
        }
        else{
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @Override
    public String registerUser(UserRegistrationRequest userRegistrationRequest){
        UserResponse existingUser = userClient.getUserByEmailOrUsername(userRegistrationRequest.getEmail(),userRegistrationRequest.getUsername()).getBody();

        if(existingUser!= null){
            throw new AlreadyExistsException("User already exists");
        }

        userRegistrationRequest.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));

        userClient.createUser(USER_REQUEST_MAPPER.mapUserToUserRequest(userRegistrationRequest));

        return "User registered successfully";

    }

    @Override
    public String registerAdmin(AdminRegistrationRequest adminRegistrationRequest){
        UserResponse existingUser = userClient.getUserByEmailOrUsername(adminRegistrationRequest.getEmail(),adminRegistrationRequest.getUsername()).getBody();
        if(existingUser!= null){
            throw new AlreadyExistsException("User already exists");
        }

        if(!adminRegistrationCode.equals(adminRegistrationRequest.getAdminCode())){
            throw new UnauthorizedException("Invalid admin registration code");
        }

        adminRegistrationRequest.setPassword(passwordEncoder.encode(adminRegistrationRequest.getPassword()));

        userClient.createUser(USER_REQUEST_MAPPER.mapAdminToUserRequest(adminRegistrationRequest));

        return "Admin registered successfully";

    }
}
