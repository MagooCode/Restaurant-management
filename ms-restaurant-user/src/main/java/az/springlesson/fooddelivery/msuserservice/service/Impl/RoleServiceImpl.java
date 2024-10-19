package az.springlesson.fooddelivery.msuserservice.service.Impl;

import az.springlesson.fooddelivery.msuserservice.dao.request.RoleRequest;
import az.springlesson.fooddelivery.msuserservice.dao.response.RoleResponse;
import az.springlesson.fooddelivery.msuserservice.exception.NotFoundException;
import az.springlesson.fooddelivery.msuserservice.model.RoleEntity;
import az.springlesson.fooddelivery.msuserservice.repository.RoleRepository;
import az.springlesson.fooddelivery.msuserservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.springlesson.fooddelivery.msuserservice.enums.ErrorMessages.ROLE_NOT_FOUND_WITH_ID;
import static az.springlesson.fooddelivery.msuserservice.enums.InfoMessages.*;
import static az.springlesson.fooddelivery.msuserservice.mapper.RoleMapper.ROLE_MAPPER;
import static java.lang.String.format;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleResponse> getAllRoles(){
        return roleRepository.findAll().stream().map(ROLE_MAPPER::mapToResponse).toList();
    }

    @Override
    public RoleResponse getRoleById(Long id){
        return roleRepository.findById(id).map(ROLE_MAPPER::mapToResponse)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                ROLE_NOT_FOUND_WITH_ID.getMessage(),
                                id
                        )
                ));
    }

    @Override
    public String createRole(RoleRequest roleRequest){
        roleRepository.save(ROLE_MAPPER.mapToEntity(roleRequest));

        return ROLE_IS_CREATED.getMessage();
    }

    @Override
    public String updateRole(Long id, RoleRequest roleRequest){
        RoleEntity roleEntity = roleRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                ROLE_NOT_FOUND_WITH_ID.getMessage(),
                                id
                        )
                ));

        roleEntity.setRoleName(roleRequest.getName().toUpperCase());
        roleEntity.setDescription(roleRequest.getDescription());
        roleRepository.save(roleEntity);

        return ROLE_IS_UPDATED.getMessage();
    }

    @Override
    public String deleteRole(Long id){
        roleRepository.deleteById(id);
        return ROLE_IS_DELETED.getMessage();
    }


}
