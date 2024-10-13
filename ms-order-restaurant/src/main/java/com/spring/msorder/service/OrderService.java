package com.spring.msorder.service;

import com.spring.msorder.dao.Requests.OrderRequests.CreateOrderRequest;
import com.spring.msorder.dao.Requests.OrderRequests.UpdateOrderRequest;
import com.spring.msorder.dao.Responses.OrderResponses.GetOrderResponse;

import java.util.List;

public interface OrderService {
    String createOrder(CreateOrderRequest createOrderRequest,String username);
    String updateOrder(Long orderId, UpdateOrderRequest updateOrderRequest);
    String deleteOrder(Long orderId);
    GetOrderResponse getOrderById(Long orderId);
    List<GetOrderResponse> getOrders();
}
