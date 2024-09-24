package az.springlesson.fooddelivery.msuserservice.service;

import az.springlesson.fooddelivery.msuserservice.dao.Request.userRequest;
import az.springlesson.fooddelivery.msuserservice.dao.Response.userResponse;
import az.springlesson.fooddelivery.msuserservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface userService {
    List<userResponse> getList();

    User create(userRequest dungeonRequest);

    userResponse getUserById(Long id);

    void updateUser(Long id, userRequest userRequest);

    void deleteUser(Long id);
}
