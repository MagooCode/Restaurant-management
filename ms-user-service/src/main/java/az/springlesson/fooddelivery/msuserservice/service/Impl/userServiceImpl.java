package az.springlesson.fooddelivery.msuserservice.service.Impl;

import az.springlesson.fooddelivery.msuserservice.dao.Request.userRequest;
import az.springlesson.fooddelivery.msuserservice.dao.Response.userResponse;
import az.springlesson.fooddelivery.msuserservice.mapper.mapper;
import az.springlesson.fooddelivery.msuserservice.model.User;
import az.springlesson.fooddelivery.msuserservice.repository.UserRepository;
import az.springlesson.fooddelivery.msuserservice.service.userService;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class userServiceImpl implements userService {

    private final UserRepository userRepository;

    public userServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<userResponse> getList() {
        return userRepository
                .findAll()
                .stream()
                .map(mapper.USER_MAPPER::mapToResponse).toList();
    }

    @Override
    public User create(userRequest userRequest) {
        return userRepository.save(mapper.USER_MAPPER.mapToEntity(userRequest));
    }

    @Override
    public userResponse getUserById(Long id) {
        return userRepository
                .findById(id)
                .map(mapper.USER_MAPPER::mapToResponse)
                .orElseThrow(()->new RuntimeException(format(String.valueOf(NOT_FOUND),id)
                ));
    }

    @Override
    public void updateUser(Long id, userRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException(format(String.valueOf(NOT_FOUND),id)));
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPasswordHash(userRequest.getPasswordHash());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setAddress(userRequest.getAddress());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException(format(String.valueOf(NOT_FOUND),id)));
        userRepository.delete(user);
    }


//    @Override
//    public userResponse updateUser(Long id, userRequest userRequest) {
//        return userRepository.findById(id)
//                .map(existingUser -> {
//                    User updatedUser = mapper.USER_MAPPER.mapToEntity(userRequest);
//                    updatedUser.setUserId(existingUser.getUserId()); //?? get set zad
//                    return userRepository.save(updatedUser);
//                })
//                .map(mapper.USER_MAPPER::mapToResponse)
//                .orElseThrow(() -> new RuntimeException(format(String.valueOf(NOT_FOUND), id)));
//    }
//
//    @Override
//    public void deleteUser(Long id) {
//        userRepository.findById(id)
//                .map(existingUser -> {
//                    userRepository.delete(existingUser);
//                    return mapper.USER_MAPPER.mapToResponse(existingUser);
//                })
//                .orElseThrow(() -> new RuntimeException(format(String.valueOf(NOT_FOUND), id)));
//    }
}
