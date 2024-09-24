package com.spring.msorder.Service.ServiceImplementations;

import com.spring.msorder.Client.RestaurantClient;
import com.spring.msorder.DAO.Requests.OrderItemRequests.CreateOrderItemRequest;
import com.spring.msorder.DAO.Responses.RestaurantResponses.MealResponse;
import com.spring.msorder.Entity.OrderItem;
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
    public String saveOrderItem(CreateOrderItemRequest createOrderItemRequest) {
        OrderItem orderItem = OrderItemMapper.ORDER_ITEM_MAPPER.mapToEntity(createOrderItemRequest);
        MealResponse mealResponse = restaurantClient.getMealById(createOrderItemRequest.getMealId()).getBody();
        orderItem.setPriceAtOrder(createOrderItemRequest.getQuantity() * mealResponse.getMealPrice());
        orderItemRepository.save(orderItem);
        return "Order item saved successfully";
    }
}
