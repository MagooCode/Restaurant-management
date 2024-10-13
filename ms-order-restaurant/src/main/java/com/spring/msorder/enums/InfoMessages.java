package com.spring.msorder.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InfoMessages {
    ORDER_CREATED("Order successfully created"),
    ORDER_UPDATED("Order successfully updated"),
    ORDER_DELETED("Order successfully deleted"),
    ORDER_ITEM_SAVED("Order item successfully saved"),
    ORDER_ITEM_DELETED("Order item successfully deleted"),
    ORDER_ITEM_UPDATED("Order item successfully updated"),
    MEAL_SET_SAVED("Meal set successfully saved"),
    MEAL_SET_DELETED("Meal set successfully deleted"),
    MEAL_SET_UPDATED("Meal set successfully updated");

    private final String message;
}
