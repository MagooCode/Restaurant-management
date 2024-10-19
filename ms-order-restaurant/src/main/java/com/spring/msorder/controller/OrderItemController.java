package com.spring.msorder.controller;

import com.spring.msorder.dao.Requests.OrderItemRequests.CreateOrderItemRequest;
import com.spring.msorder.dao.Requests.OrderItemRequests.UpdateOrderItemRequest;
import com.spring.msorder.dao.Responses.OrderItemResponses.OrderItemResponse;
import com.spring.msorder.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orderItem/")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/{id}")
    public OrderItemResponse getOrderItemById(@PathVariable Long id){
        return orderItemService.getOrderItem(id);
    }

    @GetMapping()
    public List<OrderItemResponse> getAllOrderItems(){
        return orderItemService.getOrderItems();
    }

    @PostMapping()
    public String createCustomOrderItem(@RequestBody CreateOrderItemRequest createOrderItemRequest){
        return orderItemService.saveCustomOrderItem(createOrderItemRequest);
    }

    @PostMapping("blueprint")
    public String createBlueprintOrderItem(@RequestBody CreateOrderItemRequest createOrderItemRequest){
        return orderItemService.saveBlueprintOrderItem(createOrderItemRequest);
    }

    @PutMapping("{id}")
    public String updateOrderItem(@RequestBody UpdateOrderItemRequest updateOrderItemRequest, @PathVariable Long id){
        return orderItemService.updateOrderItem(id, updateOrderItemRequest);
    }

    @PutMapping("increase/quantity/{orderItemId}")
    public void increaseOrderItemCount(@PathVariable Long orderItemId){
        orderItemService.increaseOrderItemQuantityByOne(orderItemId);
    }

    @PutMapping("decrease/quantity/{orderItemId}")
    public void decreaseOrderItemCount(@PathVariable Long orderItemId){
        orderItemService.decreaseOrderItemQuantityByOne(orderItemId);
    }

    @DeleteMapping("{id}")
    public String deleteOrderItem(@PathVariable Long id) {
        return orderItemService.deleteOrderItem(id);
    }
}
