package com.spring.msorder.dao.Requests.OrderRequests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class CreateOrderRequest {
    @NotNull(message = "Restaurant id must be provided")
    private Long restaurantId;

    private List<Long> mealSetIds;

    private List<Long> customOrderItems;


    @NotNull(message = "Discount for order must be provided")
    private Float discount;

    @NotNull(message = "Delivery address must be provided")
    private String deliveryAddress;

    @NotNull(message = "Delivery time for order must be provided")
    private LocalDateTime deliveryTime;
}
