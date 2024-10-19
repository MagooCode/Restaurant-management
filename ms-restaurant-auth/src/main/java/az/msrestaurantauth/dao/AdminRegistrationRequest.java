package az.msrestaurantauth.dao;

import lombok.Data;

@Data
public class AdminRegistrationRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String adminCode;
}
