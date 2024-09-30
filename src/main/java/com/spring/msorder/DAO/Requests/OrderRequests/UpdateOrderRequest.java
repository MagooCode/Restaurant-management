package com.spring.msorder.DAO.Requests.OrderRequests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UpdateOrderRequest {
    @NotNull(message = "Restaurant id must be provided")
    private Long restaurantId;

    @NotNull(message = "Discount for order must be provided")
    private Float discount;

    @NotNull(message = "Delivery address must be provided")
    private String deliveryAddress;

    @NotNull(message = "Delivery time for order must be provided")
    private LocalDateTime deliveryTime;
}
