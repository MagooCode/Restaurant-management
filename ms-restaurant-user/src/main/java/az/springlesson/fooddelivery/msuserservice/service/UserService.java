package az.springlesson.fooddelivery.msuserservice.service;

import az.springlesson.fooddelivery.msuserservice.dao.request.UserRequest;
import az.springlesson.fooddelivery.msuserservice.dao.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserResponse> getList();

    UserResponse create(UserRequest dungeonRequest);

    UserResponse getUserById(Long id);

    void updateUser(Long id, UserRequest userRequest);

    void deleteUser(Long id);

    Long getIdByUsername(String username);

    UserResponse getUserByUsername(String username);

    UserResponse getUserByEmailOrUsername(String email, String username);
}
