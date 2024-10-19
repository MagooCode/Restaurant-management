package az.springlesson.travel.msrestaurant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InfoMessage {
    RESTAURANT_CREATED("Restaurant created successfully"),
    RESTAURANT_UPDATED("Restaurant updated successfully"),
    RESTAURANT_DELETED("Restaurant deleted successfully"),
    REVIEW_CREATED("Review created successfully"),
    MEAL_CREATED("Meal created successfully"),
    MEAL_UPDATED("Meal updated successfully"),
    MEAL_DELETED("Meal deleted successfully"),
    MENU_CREATED("Menu created successfully"),
    MENU_UPDATED("Menu updated successfully"),
    MENU_DELETED("Menu deleted successfully");

    private final String message;
}
