package com.spring.msorder.mapper;


import com.spring.msorder.dao.Requests.OrderItemRequests.CreateOrderItemRequest;
import com.spring.msorder.dao.Requests.OrderItemRequests.UpdateOrderItemRequest;
import com.spring.msorder.dao.Responses.OrderItemResponses.OrderItemResponse;
import com.spring.msorder.entity.OrderItem;

public enum OrderItemMapper {
    ORDER_ITEM_MAPPER;

    public OrderItem mapToEntity(CreateOrderItemRequest createOrderItemRequest) {
        return OrderItem.builder()
                .mealId(createOrderItemRequest.getMealId())
                .quantity(createOrderItemRequest.getQuantity()).build();
    }

    public OrderItem mapToEntityToUpdate(OrderItem orderItemToUpdate, UpdateOrderItemRequest updateOrderItemRequest) {
        if (updateOrderItemRequest.getMealId() != null) {
            orderItemToUpdate.setMealId(updateOrderItemRequest.getMealId());
        }

        if (updateOrderItemRequest.getQuantity() != null) {
            orderItemToUpdate.setQuantity(updateOrderItemRequest.getQuantity());
        }

        return orderItemToUpdate;
    }

    public OrderItemResponse mapOrderItemToResponse(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .id(orderItem.getId())
                .mealId(orderItem.getMealId())
                .quantity(orderItem.getQuantity())
                .orderItemType(orderItem.getOrderItemType())
                .priceAtOrder(orderItem.getPriceAtOrder())
                .build();
    }
}
