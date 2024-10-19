package com.spring.msorder.dao.Responses.OrderItemResponses;

import com.spring.msorder.enums.OrderItemType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemResponse {
    private Long id;

    private Long mealId;

    private Byte quantity;

    private OrderItemType orderItemType;

    private Float priceAtOrder;
}
