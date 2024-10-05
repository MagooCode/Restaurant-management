package com.spring.msorder.DAO.Responses.OrderItemResponses;

import com.spring.msorder.Enum.OrderItemType;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetOrderItemResponse {
    private Long id;

    private Long mealId;

    private Byte quantity;

    private OrderItemType orderItemType;

    private Float priceAtOrder;
}
