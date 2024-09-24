package com.spring.msorder.Service;

import com.spring.msorder.DAO.Requests.OrderItemRequests.CreateOrderItemRequest;

public interface OrderItemService {
    String saveOrderItem(CreateOrderItemRequest createOrderItemRequest);
}
