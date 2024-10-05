package com.spring.msorder.DAO.Responses.OrderResponses;

import com.spring.msorder.Enum.OrderStatus;
import com.spring.msorder.Enum.PaymentStatus;
import jakarta.persistence.Column;
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
