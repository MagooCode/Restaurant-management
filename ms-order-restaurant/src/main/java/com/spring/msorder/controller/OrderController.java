package com.spring.msorder.controller;

import com.spring.msorder.dao.Requests.OrderRequests.CreateOrderRequest;
import com.spring.msorder.dao.Requests.OrderRequests.UpdateOrderRequest;
import com.spring.msorder.dao.Responses.OrderResponses.GetOrderResponse;
import com.spring.msorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order/")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public GetOrderResponse getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @GetMapping()
    public List<GetOrderResponse> getAllOrders(){
        return orderService.getOrders();
    }

    @PostMapping()
    public String createOrder(@RequestBody CreateOrderRequest createOrderRequest,@RequestHeader String username){
        return orderService.createOrder(createOrderRequest,username);
    }

    @PutMapping("{id}")
    public String updateOrder(@RequestBody UpdateOrderRequest updateOrderRequest, @PathVariable Long id,@RequestHeader String username){
        return orderService.updateOrder(id, updateOrderRequest,username);
    }

    @DeleteMapping("{id}")
    public String deleteOrderItem(@PathVariable Long id,@RequestHeader String username) {
        return orderService.deleteOrder(id,username);
    }
}
