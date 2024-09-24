package com.spring.msorder.Mapper;


import com.spring.msorder.DAO.Requests.OrderItemRequests.CreateOrderItemRequest;
import com.spring.msorder.Entity.OrderItem;

public enum OrderItemMapper {
    ORDER_ITEM_MAPPER;

    public OrderItem mapToEntity(CreateOrderItemRequest createOrderItemRequest) {
        return OrderItem.builder()
                .mealId(createOrderItemRequest.getMealId())
                .quantity(createOrderItemRequest.getQuantity()).build();
    }
}
