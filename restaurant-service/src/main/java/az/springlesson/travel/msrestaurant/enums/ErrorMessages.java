package az.springlesson.travel.msrestaurant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {
    MEAL_NOT_FOUND("Meal not found with given id : %s"),
    MENU_NOT_FOUND("Menu not found with given id : %s"),
    ACTION_UNAUTHORIZED("You are not authorized to perform this action"),
    RESTAURANT_NOT_FOUND("Restaurant not found with given id : %s");

    private final String message;
}
