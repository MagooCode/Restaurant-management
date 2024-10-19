package az.msrestaurantauth.mapper;

import az.msrestaurantauth.dao.AdminRegistrationRequest;
import az.msrestaurantauth.dao.UserRegistrationRequest;
import az.msrestaurantauth.dao.UserRequest;
import lombok.Builder;
import lombok.Data;

public enum UserRequestMapper {
    USER_REQUEST_MAPPER;

    public UserRequest mapAdminToUserRequest(AdminRegistrationRequest adminRegistrationRequest){
        return UserRequest.builder()
                .username(adminRegistrationRequest.getUsername())
                .password(adminRegistrationRequest.getPassword())
                .firstName(adminRegistrationRequest.getFirstName())
                .lastName(adminRegistrationRequest.getLastName())
                .email(adminRegistrationRequest.getEmail())
                .phoneNumber(adminRegistrationRequest.getPhoneNumber())
                .address(adminRegistrationRequest.getAddress())
                .roleName("Admin")
                .build();
    }

    public UserRequest mapUserToUserRequest(UserRegistrationRequest userRegistrationRequest){
        return UserRequest.builder()
                .username(userRegistrationRequest.getUsername())
                .password(userRegistrationRequest.getPassword())
                .firstName(userRegistrationRequest.getFirstName())
                .lastName(userRegistrationRequest.getLastName())
                .email(userRegistrationRequest.getEmail())
                .phoneNumber(userRegistrationRequest.getPhoneNumber())
                .address(userRegistrationRequest.getAddress())
                .roleName("User")
                .build();
    }
}
