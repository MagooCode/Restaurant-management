package az.springlesson.fooddelivery.msuserservice.dao.Request;

import az.springlesson.fooddelivery.msuserservice.model.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class userRequest {
    @NotBlank(message = "Name cant be null")
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private String address;
    private List<Role> roles;
}
