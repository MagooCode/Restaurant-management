package com.spring.msorder.Service.ServiceImplementations;

import com.spring.msorder.Client.RestaurantClient;
import com.spring.msorder.DAO.Requests.OrderItemRequests.CreateOrderItemRequest;
import com.spring.msorder.DAO.Requests.OrderItemRequests.UpdateOrderItemRequest;
import com.spring.msorder.DAO.Responses.RestaurantResponses.MealResponse;
import com.spring.msorder.Entity.OrderItem;
import com.spring.msorder.Enum.OrderItemType;
import com.spring.msorder.Mapper.OrderItemMapper;
import com.spring.msorder.Repository.OrderItemRepository;
import com.spring.msorder.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImplementation implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final RestaurantClient restaurantClient;

    @Autowired
    public OrderItemServiceImplementation(OrderItemRepository orderItemRepository, RestaurantClient restaurantClient) {
        this.orderItemRepository = orderItemRepository;
        this.restaurantClient = restaurantClient;
    }

    @Override
    public String saveCustomOrderItem(CreateOrderItemRequest createOrderItemRequest) {
        OrderItem orderItem = OrderItemMapper.ORDER_ITEM_MAPPER.mapToEntity(createOrderItemRequest);
        MealResponse mealResponse = restaurantClient.getMealById(createOrderItemRequest.getMealId()).getBody();
        orderItem.setPriceAtOrder(createOrderItemRequest.getQuantity() * mealResponse.getMealPrice());
        orderItem.setOrderItemType(OrderItemType.CUSTOM);
        orderItemRepository.save(orderItem);
        return "Order item saved successfully";
    }

    @Override
    public String saveBlueprintOrderItem(CreateOrderItemRequest createOrderItemRequest) {
        OrderItem orderItem = OrderItemMapper.ORDER_ITEM_MAPPER.mapToEntity(createOrderItemRequest);
        MealResponse mealResponse = restaurantClient.getMealById(createOrderItemRequest.getMealId()).getBody();
        orderItem.setPriceAtOrder(createOrderItemRequest.getQuantity() * mealResponse.getMealPrice());
        orderItem.setOrderItemType(OrderItemType.BLUEPRINT);
        orderItemRepository.save(orderItem);
        return "Order item saved successfully";
    }

    @Override
    public String updateOrderItem(Long orderItemId, UpdateOrderItemRequest updateOrderItemRequest) {
        OrderItem orderItemToUpdate = orderItemRepository.findById(orderItemId).get();
        OrderItem updatedOrderItem = OrderItemMapper.ORDER_ITEM_MAPPER.mapToEntityToUpdate(orderItemToUpdate, updateOrderItemRequest);
        orderItemRepository.save(updatedOrderItem);
        return "Order item updated successfully";
    }

    @Override
    public String deleteOrderItem(Long orderItemId) {
        OrderItem orderItemToDelete = orderItemRepository.findById(orderItemId).get();
        orderItemRepository.delete(orderItemToDelete);
        return "Order item deleted successfully";
    }

    @Override
    public OrderItem getOrderItem(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).get();
        return orderItem;
    }

    @Override
    public List<OrderItem> getOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems;
    }


}
