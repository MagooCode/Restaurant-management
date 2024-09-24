package az.springlesson.fooddelivery.msuserservice.mapper;

import az.springlesson.fooddelivery.msuserservice.dao.Request.userRequest;
import az.springlesson.fooddelivery.msuserservice.dao.Response.userResponse;
import az.springlesson.fooddelivery.msuserservice.model.User;

public enum mapper {
    USER_MAPPER;

    public User mapToEntity(userRequest userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .passwordHash(userRequest.getPasswordHash())
                .phoneNumber(userRequest.getPhoneNumber())
                .address(userRequest.getAddress())
                .build();
    }

    public userResponse mapToResponse(User user) {
        return userResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .passwordHash(user.getPasswordHash())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .build();
    }
}
