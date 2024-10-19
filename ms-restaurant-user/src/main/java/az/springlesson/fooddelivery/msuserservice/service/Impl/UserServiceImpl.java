package az.springlesson.fooddelivery.msuserservice.service.Impl;

import az.springlesson.fooddelivery.msuserservice.dao.request.UserRequest;
import az.springlesson.fooddelivery.msuserservice.dao.response.UserResponse;
import az.springlesson.fooddelivery.msuserservice.exception.NotFoundException;
import az.springlesson.fooddelivery.msuserservice.model.RoleEntity;
import az.springlesson.fooddelivery.msuserservice.model.UserEntity;
import az.springlesson.fooddelivery.msuserservice.repository.RoleRepository;
import az.springlesson.fooddelivery.msuserservice.repository.UserRepository;
import az.springlesson.fooddelivery.msuserservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.springlesson.fooddelivery.msuserservice.enums.ErrorMessages.*;
import static az.springlesson.fooddelivery.msuserservice.mapper.UserMapper.USER_MAPPER;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserResponse> getList() {
        return userRepository
                .findAll()
                .stream()
                .map(USER_MAPPER::mapToResponse).toList();
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        RoleEntity roleEntity = roleRepository.findByRoleName(userRequest.getRoleName().toUpperCase())
                .orElseThrow(()-> new NotFoundException(
                        format(
                                ROLE_NOT_FOUND_WITH_NAME.getMessage(),
                                userRequest.getRoleName()
                        )
                ));
        UserEntity userEntity =  userRepository.save(USER_MAPPER.mapToEntity(userRequest,roleEntity));
        return USER_MAPPER.mapToResponse(userEntity);
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository
                .findById(id)
                .map(USER_MAPPER::mapToResponse)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                USER_NOT_FOUND.getMessage(),
                                id
                        )
                        )
                );
    }

    @Override
    public void updateUser(Long id, UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                USER_NOT_FOUND.getMessage(),
                                id
                        )
                ));
        userEntity.setFirstName(userRequest.getFirstName());
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setLastName(userRequest.getLastName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userEntity.setAddress(userRequest.getAddress());
        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                        .orElseThrow(()-> new NotFoundException(
                                format(
                                        USER_NOT_FOUND.getMessage(),
                                        id
                                )
                        ));
        userRepository.delete(userEntity);
    }

    @Override
    public Long getIdByUsername(String username) {
        UserEntity userEntity =  userRepository.findByUsername(username)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                USER_NOT_FOUND_WITH_USERNAME.getMessage(),
                                username
                        )
                ));

        return userEntity.getUserId();
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(USER_MAPPER::mapToResponse)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                USER_NOT_FOUND_WITH_USERNAME.getMessage(),
                                username
                        )
                ));
    }

    @Override
    public UserResponse getUserByEmailOrUsername(String email, String username) {
        return userRepository.findByEmailOrUsername(email,username).map(USER_MAPPER::mapToResponse)
                .orElse(null);
    }

}
