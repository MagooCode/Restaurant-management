package az.springlesson.fooddelivery.msuserservice.mapper;

import az.springlesson.fooddelivery.msuserservice.dao.request.RoleRequest;
import az.springlesson.fooddelivery.msuserservice.dao.response.RoleResponse;
import az.springlesson.fooddelivery.msuserservice.model.RoleEntity;

public enum RoleMapper {
    ROLE_MAPPER;

    public RoleResponse mapToResponse(RoleEntity role) {
        return RoleResponse.builder()
                .id(role.getRoleId())
                .name(role.getRoleName().toUpperCase())
                .description(role.getDescription())
                .build();
    }

    public RoleEntity mapToEntity(RoleRequest roleRequest) {
        return RoleEntity.builder()
                .roleName(roleRequest.getName().toUpperCase())
                .description(roleRequest.getDescription())
                .build();
    }
}
