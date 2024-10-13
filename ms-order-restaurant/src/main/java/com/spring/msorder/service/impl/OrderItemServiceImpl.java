package com.spring.msorder.service.impl;

import com.spring.msorder.client.RestaurantClient;
import com.spring.msorder.dao.Requests.OrderItemRequests.CreateOrderItemRequest;
import com.spring.msorder.dao.Requests.OrderItemRequests.UpdateOrderItemRequest;
import com.spring.msorder.dao.Responses.OrderItemResponses.OrderItemResponse;
import com.spring.msorder.dao.Responses.RestaurantResponses.MealResponse;
import com.spring.msorder.entity.MealSet;
import com.spring.msorder.entity.Order;
import com.spring.msorder.entity.OrderItem;
import com.spring.msorder.enums.OrderItemType;
import com.spring.msorder.exception.NotFoundException;
import com.spring.msorder.repository.MealSetRepository;
import com.spring.msorder.repository.OrderItemRepository;
import com.spring.msorder.repository.OrderRepository;
import com.spring.msorder.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.spring.msorder.enums.ErrorMessages.*;
import static com.spring.msorder.enums.InfoMessages.*;
import static com.spring.msorder.mapper.OrderItemMapper.ORDER_ITEM_MAPPER;
import static java.lang.String.format;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final MealSetRepository mealSetRepository;
    private final RestaurantClient restaurantClient;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderRepository orderRepository, RestaurantClient restaurantClient, MealSetRepository mealSetRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.restaurantClient = restaurantClient;
        this.mealSetRepository = mealSetRepository;
    }

    @Override
    public String saveCustomOrderItem(CreateOrderItemRequest createOrderItemRequest) {
        OrderItem orderItem = ORDER_ITEM_MAPPER.mapToEntity(createOrderItemRequest);
        MealResponse mealResponse = restaurantClient.getMealById(createOrderItemRequest.getMealId()).getBody();
        orderItem.setPriceAtOrder(createOrderItemRequest.getQuantity() * mealResponse.getMealPrice());
        orderItem.setOrderItemType(OrderItemType.CUSTOM);
        orderItemRepository.save(orderItem);
        return ORDER_ITEM_SAVED.getMessage();
    }

    @Override
    public String saveBlueprintOrderItem(CreateOrderItemRequest createOrderItemRequest) {
        OrderItem orderItem = ORDER_ITEM_MAPPER.mapToEntity(createOrderItemRequest);
        MealResponse mealResponse = restaurantClient.getMealById(createOrderItemRequest.getMealId()).getBody();
        orderItem.setPriceAtOrder(createOrderItemRequest.getQuantity() * mealResponse.getMealPrice());
        orderItem.setOrderItemType(OrderItemType.BLUEPRINT);
        orderItemRepository.save(orderItem);
        return ORDER_ITEM_SAVED.getMessage();
    }

    @Override
    public String updateOrderItem(Long orderItemId, UpdateOrderItemRequest updateOrderItemRequest) {
        OrderItem orderItemToUpdate = findOrderItemById(orderItemId);
        OrderItem updatedOrderItem = ORDER_ITEM_MAPPER.mapToEntityToUpdate(orderItemToUpdate, updateOrderItemRequest);
        orderItemRepository.save(updatedOrderItem);
        return ORDER_ITEM_UPDATED.getMessage();
    }

    @Override
    public String deleteOrderItem(Long orderItemId) {
        OrderItem orderItemToDelete = findOrderItemById(orderItemId);
        orderItemRepository.delete(orderItemToDelete);
        return ORDER_ITEM_DELETED.getMessage();
    }

    @Override
    public void increaseOrderItemQuantityByOne(Long orderItemId) {
        OrderItem orderItemToIncrease = findOrderItemById(orderItemId);
        byte newQuantity = (byte) (orderItemToIncrease.getQuantity() + 1);
        orderItemToIncrease.setQuantity(newQuantity);
        orderItemRepository.save(orderItemToIncrease);
    }

    @Override
    public void decreaseOrderItemQuantityByOne(Long orderItemId) {
        OrderItem orderItemToDecrease = findOrderItemById(orderItemId);
        byte newQuantity = (byte) (orderItemToDecrease.getQuantity() - 1);
        orderItemToDecrease.setQuantity(newQuantity);
        orderItemRepository.save(orderItemToDecrease);
    }

    @Override
    public OrderItemResponse getOrderItem(Long orderItemId) {
        OrderItem orderItem = findOrderItemById(orderItemId);
        return ORDER_ITEM_MAPPER.mapOrderItemToResponse(orderItem);
    }

    @Override
    public List<OrderItemResponse> getOrderItems() {
        return orderItemRepository.findAll().stream().map(ORDER_ITEM_MAPPER::mapOrderItemToResponse).toList();
    }

    @Override
    public void assignOrderItemToMealSet(Long orderItemId, Long mealSetId) {
        MealSet mealSet = mealSetRepository.findById(mealSetId)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                MEAL_SET_NOT_FOUND.getMessage(),
                                mealSetId
                        )
                ));

        OrderItem orderItemToAssign = findOrderItemById(orderItemId);
        mealSet.getOrderItems().add(orderItemToAssign);

        float totalPrice = 0;
        for (OrderItem orderItem : mealSet.getOrderItems()) {
            totalPrice += orderItem.getPriceAtOrder();
        }
        mealSet.setTotalPrice(totalPrice);
        mealSetRepository.save(mealSet);
    }

    @Override
    public void assignOrderItemToOrder(Long orderId, Long orderItemId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                ORDER_NOT_FOUND.getMessage(),
                                orderId
                        )
                ));

        OrderItem orderItemToAssign = findOrderItemById(orderItemId);

        order.getOrderItems().add(orderItemToAssign);

        float customOrderItemsPrice = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            customOrderItemsPrice += orderItem.getPriceAtOrder();
        }

        order.setTotalPrice(customOrderItemsPrice + order.getMealSetsPrice());
        orderRepository.save(order);
    }


    private OrderItem findOrderItemById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                ORDER_ITEM_NOT_FOUND.getMessage(),
                                orderItemId
                        )
                ));
    }

}
