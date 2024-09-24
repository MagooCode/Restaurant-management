package az.springlesson.fooddelivery.msuserservice.dao.Response;

import az.springlesson.fooddelivery.msuserservice.model.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class userResponse {
    @NotBlank(message = "Name cant be null")
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private String address;
    private List<Role> roles;
}
