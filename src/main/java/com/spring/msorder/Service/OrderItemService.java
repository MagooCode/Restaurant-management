package com.spring.msorder.Service;

import com.spring.msorder.DAO.Requests.OrderItemRequests.CreateOrderItemRequest;
import com.spring.msorder.DAO.Requests.OrderItemRequests.UpdateOrderItemRequest;
import com.spring.msorder.Entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    String saveCustomOrderItem(CreateOrderItemRequest createOrderItemRequest);
    String saveBlueprintOrderItem(CreateOrderItemRequest createOrderItemRequest);
    String updateOrderItem(Long orderItemId, UpdateOrderItemRequest updateOrderItemRequest);
    String deleteOrderItem(Long orderItemId);
    OrderItem getOrderItem(Long orderItemId);
    List<OrderItem> getOrderItems();
}
