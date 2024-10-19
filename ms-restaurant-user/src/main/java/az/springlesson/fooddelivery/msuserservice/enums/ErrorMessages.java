package az.springlesson.fooddelivery.msuserservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {
    USER_NOT_FOUND ("User not found with given id %s"),
    ROLE_NOT_FOUND_WITH_ID("Role not found with given id %s"),
    ROLE_NOT_FOUND_WITH_NAME("Role not found with given name %s"),
    USER_NOT_FOUND_WITH_USERNAME ("User not found with username %s");

    private final String message;
}
