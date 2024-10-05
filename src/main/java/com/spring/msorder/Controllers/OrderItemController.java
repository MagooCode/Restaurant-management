package com.spring.msorder.Controllers;

import com.spring.msorder.DAO.Requests.OrderItemRequests.CreateOrderItemRequest;
import com.spring.msorder.DAO.Requests.OrderItemRequests.UpdateOrderItemRequest;
import com.spring.msorder.DAO.Responses.OrderItemResponses.GetOrderItemResponse;
import com.spring.msorder.Service.ServiceImplementations.OrderItemServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orderItems")
public class OrderItemController {
    private final OrderItemServiceImplementation orderItemServiceImplementation;

    @Autowired
    public OrderItemController(OrderItemServiceImplementation orderItemServiceImplementation) {
        this.orderItemServiceImplementation = orderItemServiceImplementation;
    }

    @GetMapping("/{id}")
    public GetOrderItemResponse getOrderItemById(@PathVariable Long id){
        return orderItemServiceImplementation.getOrderItem(id);
    }

    @GetMapping()
    public List<GetOrderItemResponse> getAllOrderItems(){
        return orderItemServiceImplementation.getOrderItems();
    }

    @PostMapping()
    public String createCustomOrderItem(@RequestBody CreateOrderItemRequest createOrderItemRequest){
        return orderItemServiceImplementation.saveCustomOrderItem(createOrderItemRequest);
    }

    @PostMapping()
    public String createBlueprintOrderItem(@RequestBody CreateOrderItemRequest createOrderItemRequest){
        return orderItemServiceImplementation.saveBlueprintOrderItem(createOrderItemRequest);
    }

    @PutMapping("{id}")
    public String updateOrderItem(@RequestBody UpdateOrderItemRequest updateOrderItemRequest, @PathVariable Long id){
        return orderItemServiceImplementation.updateOrderItem(id, updateOrderItemRequest);
    }

    @DeleteMapping("{id}")
    public String deleteOrderItem(@PathVariable Long id) {
        return orderItemServiceImplementation.deleteOrderItem(id);
    }
}
