package com.spring.msorder.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {
    ORDER_NOT_FOUND("Order not found with given id %s"),
    ORDER_ITEM_NOT_FOUND("Order item not found with given id %s"),
    MEAL_SET_NOT_FOUND("Meal set not found with given id %s");

    private final String message;
}
