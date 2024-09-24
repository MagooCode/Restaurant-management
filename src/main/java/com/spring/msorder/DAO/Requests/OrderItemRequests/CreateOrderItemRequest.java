package com.spring.msorder.DAO.Requests.OrderItemRequests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreateOrderItemRequest {

    @NotNull(message = "Meal id must be provided")
    private Long mealId;

    @NotNull(message = "Meal quantity must be provided")
    private Byte quantity;

}
