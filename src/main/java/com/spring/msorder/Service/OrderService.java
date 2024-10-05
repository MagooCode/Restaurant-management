package com.spring.msorder.Service;

import com.spring.msorder.DAO.Requests.OrderRequests.CreateOrderRequest;
import com.spring.msorder.DAO.Requests.OrderRequests.UpdateOrderRequest;
import com.spring.msorder.DAO.Responses.OrderResponses.GetOrderResponse;
import com.spring.msorder.Entity.Order;

import java.util.List;

public interface OrderService {
    String createOrder(CreateOrderRequest createOrderRequest);
    String updateOrder(Long orderId, UpdateOrderRequest updateOrderRequest);
    String deleteOrder(Long orderId);
    GetOrderResponse getOrderbyId(Long orderId);
    List<GetOrderResponse> getOrders();
}
