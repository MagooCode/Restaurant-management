package com.spring.msorder.Controllers;

import com.spring.msorder.DAO.Requests.OrderItemRequests.CreateOrderItemRequest;
import com.spring.msorder.DAO.Requests.OrderItemRequests.UpdateOrderItemRequest;
import com.spring.msorder.DAO.Requests.OrderRequests.CreateOrderRequest;
import com.spring.msorder.DAO.Requests.OrderRequests.UpdateOrderRequest;
import com.spring.msorder.DAO.Responses.OrderItemResponses.GetOrderItemResponse;
import com.spring.msorder.DAO.Responses.OrderResponses.GetOrderResponse;
import com.spring.msorder.Service.ServiceImplementations.OrderItemServiceImplementation;
import com.spring.msorder.Service.ServiceImplementations.OrderServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderServiceImplementation orderServiceImplementation;

    @Autowired
    public OrderController(OrderServiceImplementation orderServiceImplementation) {
        this.orderServiceImplementation = orderServiceImplementation;
    }

    @GetMapping("/{id}")
    public GetOrderResponse getOrderById(@PathVariable Long id){
        return orderServiceImplementation.getOrderbyId(id);
    }

    @GetMapping()
    public List<GetOrderResponse> getAllOrders(){
        return orderServiceImplementation.getOrders();
    }

    @PostMapping()
    public String createOrder(@RequestBody CreateOrderRequest createOrderRequest){
        return orderServiceImplementation.createOrder(createOrderRequest);
    }

    @PutMapping("{id}")
    public String updateOrder(@RequestBody UpdateOrderRequest updateOrderRequest, @PathVariable Long id){
        return orderServiceImplementation.updateOrder(id, updateOrderRequest);
    }

    @DeleteMapping("{id}")
    public String deleteOrderItem(@PathVariable Long id) {
        return orderServiceImplementation.deleteOrder(id);
    }
}
