package com.spring.msorder.dao.Responses.OrderResponses;

import com.spring.msorder.enums.OrderStatus;
import com.spring.msorder.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class GetOrderResponse {
    private Long id;

    private Long restaurantId;

    private Float totalPrice;

    private Float customOrderItemsPrice;

    private Float mealSetsPrice;

    private OrderStatus orderStatus;

    private PaymentStatus paymentStatus;

    private Float discount;

    private String deliveryAddress;

    private LocalDateTime deliveryTime;
}
