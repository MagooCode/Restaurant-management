package az.springlesson.travel.msrestaurant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InfoMessage {
    MEAL_CREATED("Meal created successfully"),
    MEAL_UPDATED("Meal updated successfully"),
    MEAL_DELETED("Meal deleted successfully"),
    MENU_CREATED("Menu created successfully"),
    MENU_UPDATED("Menu updated successfully"),
    MENU_DELETED("Menu deleted successfully");

    private final String message;
}
