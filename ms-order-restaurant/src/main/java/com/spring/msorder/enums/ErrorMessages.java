package com.spring.msorder.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {
    ORDER_NOT_FOUND("Order not found with given id %s"),
    ORDER_ITEM_NOT_FOUND("Order item not found with given id %s"),
    ENTER_DISCOUNT_TOTAL_PRICE_CORRECT("Please enter discount total price correct"),
    ACTION_UNAUTHORIZED("You are not authorized to perform this action"),
    MEAL_SET_NOT_FOUND("Meal set not found with given id %s");

    private final String message;
}
