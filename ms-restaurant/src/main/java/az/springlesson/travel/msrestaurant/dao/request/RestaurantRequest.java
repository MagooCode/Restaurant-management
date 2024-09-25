package az.springlesson.travel.msrestaurant.dao.request;

import az.springlesson.travel.msrestaurant.entity.Details;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RestaurantRequest {
    @NotBlank(message = "Restaurant name is required")
    private String restaurantName;

    @NotBlank(message = "Restaurant address is required")
    private String restaurantAddress;

    @Embedded
    @NotBlank(message = "Description can't be unwritten")
    private Details details;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Owner of the restaurant must be written")
    private Long ownerId;

}
