package com.spring.msorder.Service;

import com.spring.msorder.DAO.Requests.OrderItemRequests.CreateOrderItemRequest;
import com.spring.msorder.DAO.Requests.OrderItemRequests.UpdateOrderItemRequest;
import com.spring.msorder.DAO.Responses.OrderItemResponses.GetOrderItemResponse;
import com.spring.msorder.Entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    String saveCustomOrderItem(CreateOrderItemRequest createOrderItemRequest);
    String saveBlueprintOrderItem(CreateOrderItemRequest createOrderItemRequest);
    String updateOrderItem(Long orderItemId, UpdateOrderItemRequest updateOrderItemRequest);
    String deleteOrderItem(Long orderItemId);
    void increaseOrderItemQuantityByOne(Long orderItemId);
    void decreaseOrderItemQuantityByOne(Long orderItemId);
    GetOrderItemResponse getOrderItem(Long orderItemId);
    List<GetOrderItemResponse> getOrderItems();
    void assignOrderItemToMealSet(Long orderItemId, Long mealSetId);
    void assignOrderItemToOrder(Long orderId, Long orderItemId);
}
