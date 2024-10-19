package com.spring.msorder.service;

import com.spring.msorder.dao.Requests.OrderItemRequests.CreateOrderItemRequest;
import com.spring.msorder.dao.Requests.OrderItemRequests.UpdateOrderItemRequest;
import com.spring.msorder.dao.Responses.OrderItemResponses.OrderItemResponse;

import java.util.List;

public interface OrderItemService {
    String saveCustomOrderItem(CreateOrderItemRequest createOrderItemRequest);
    String saveBlueprintOrderItem(CreateOrderItemRequest createOrderItemRequest);
    String updateOrderItem(Long orderItemId, UpdateOrderItemRequest updateOrderItemRequest);
    String deleteOrderItem(Long orderItemId);
    void increaseOrderItemQuantityByOne(Long orderItemId);
    void decreaseOrderItemQuantityByOne(Long orderItemId);
    OrderItemResponse getOrderItem(Long orderItemId);
    List<OrderItemResponse> getOrderItems();
    void assignOrderItemToMealSet(Long orderItemId, Long mealSetId);
    void assignOrderItemToOrder(Long orderId, Long orderItemId);
}
