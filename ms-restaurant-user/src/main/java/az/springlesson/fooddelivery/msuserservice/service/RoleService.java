package az.springlesson.fooddelivery.msuserservice.service;

import az.springlesson.fooddelivery.msuserservice.dao.request.RoleRequest;
import az.springlesson.fooddelivery.msuserservice.dao.response.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getAllRoles();

    RoleResponse getRoleById(Long id);

    String createRole(RoleRequest roleRequest);

    String updateRole(Long id, RoleRequest roleRequest);

    String deleteRole(Long id);
}
