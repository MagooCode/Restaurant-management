package com.spring.msorder.Mapper;


import com.spring.msorder.DAO.Requests.OrderItemRequests.CreateOrderItemRequest;
import com.spring.msorder.DAO.Requests.OrderItemRequests.UpdateOrderItemRequest;
import com.spring.msorder.DAO.Responses.OrderItemResponses.GetOrderItemResponse;
import com.spring.msorder.Entity.OrderItem;

public enum OrderItemMapper {
    ORDER_ITEM_MAPPER;

    public OrderItem mapToEntity(CreateOrderItemRequest createOrderItemRequest) {
        return OrderItem.builder()
                .mealId(createOrderItemRequest.getMealId())
                .quantity(createOrderItemRequest.getQuantity()).build();
    }

//    public OrderItem mapToEntityToUpdate(OrderItem orderItemToUpdate, UpdateOrderItemRequest updateOrderItemRequest) {
//        return OrderItem.builder()
//                .mealId(updateOrderItemRequest.getMealId())
//                .quantity(updateOrderItemRequest.getQuantity()).build();
//    }
    public OrderItem mapToEntityToUpdate(OrderItem orderItemToUpdate, UpdateOrderItemRequest updateOrderItemRequest) {
        if (updateOrderItemRequest.getMealId() != null) {
            orderItemToUpdate.setMealId(updateOrderItemRequest.getMealId());
        }

        if (updateOrderItemRequest.getQuantity() != null) {
            orderItemToUpdate.setQuantity(updateOrderItemRequest.getQuantity());
        }

        return orderItemToUpdate;
    }

    public GetOrderItemResponse mapOrderItemToResponse(OrderItem orderItem) {
        return GetOrderItemResponse.builder()
                .id(orderItem.getId())
                .mealId(orderItem.getMealId())
                .quantity(orderItem.getQuantity())
                .orderItemType(orderItem.getOrderItemType())
                .priceAtOrder(orderItem.getPriceAtOrder())
                .build();
    }
}
