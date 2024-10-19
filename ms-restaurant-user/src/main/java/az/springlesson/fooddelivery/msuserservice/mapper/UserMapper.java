package az.springlesson.fooddelivery.msuserservice.mapper;

import az.springlesson.fooddelivery.msuserservice.dao.request.UserRequest;
import az.springlesson.fooddelivery.msuserservice.dao.response.UserResponse;
import az.springlesson.fooddelivery.msuserservice.model.RoleEntity;
import az.springlesson.fooddelivery.msuserservice.model.UserEntity;

import java.util.List;

public enum UserMapper {
    USER_MAPPER;

    public UserEntity mapToEntity(UserRequest userRequest, RoleEntity roleEntity) {
        return UserEntity.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .address(userRequest.getAddress())
                .role(roleEntity)
                .build();
    }

    public UserResponse mapToResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getUserId())
                .username(userEntity.getUsername())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .phoneNumber(userEntity.getPhoneNumber())
                .address(userEntity.getAddress())
                .roleName(userEntity.getRole().getRoleName())
                .build();
    }
}
