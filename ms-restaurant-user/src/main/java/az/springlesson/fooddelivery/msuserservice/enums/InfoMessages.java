package az.springlesson.fooddelivery.msuserservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InfoMessages {
    ROLE_IS_CREATED("Role is created successfully"),
    ROLE_IS_UPDATED("Role is updated successfully"),
    ROLE_IS_DELETED("Role is deleted successfully");

    private final String message;
}
