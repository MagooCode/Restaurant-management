package com.spring.msorder.service.impl;

import com.spring.msorder.client.UserClient;
import com.spring.msorder.dao.Requests.OrderRequests.CreateOrderRequest;
import com.spring.msorder.dao.Requests.OrderRequests.UpdateOrderRequest;
import com.spring.msorder.dao.Responses.OrderResponses.GetOrderResponse;
import com.spring.msorder.dao.Responses.UserResponse;
import com.spring.msorder.entity.MealSet;
import com.spring.msorder.entity.Order;
import com.spring.msorder.entity.OrderItem;
import com.spring.msorder.enums.OrderStatus;
import com.spring.msorder.events.OrderConfirmationEvent;
import com.spring.msorder.exception.NotFoundException;
import com.spring.msorder.producer.OrderProducer;
import com.spring.msorder.repository.MealSetRepository;
import com.spring.msorder.repository.OrderItemRepository;
import com.spring.msorder.repository.OrderRepository;
import com.spring.msorder.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.spring.msorder.enums.ErrorMessages.ORDER_NOT_FOUND;
import static com.spring.msorder.enums.InfoMessages.*;
import static com.spring.msorder.mapper.OrderMapper.*;
import static java.lang.String.format;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MealSetRepository mealSetRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserClient userClient;
    private final OrderProducer orderProducer;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, MealSetRepository mealSetRepository, OrderItemRepository orderItemRepository, UserClient userClient, OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.mealSetRepository = mealSetRepository;
        this.orderItemRepository = orderItemRepository;
        this.userClient = userClient;
        this.orderProducer = orderProducer;
    }

    @Override
    @Transactional
    public String createOrder(CreateOrderRequest createOrderRequest,String username) {
        List<MealSet> mealSetList = mealSetRepository.findAllById(createOrderRequest.getMealSetIds());
        List<OrderItem> orderItemList = orderItemRepository.findAllById(createOrderRequest.getCustomOrderItems());
        Float mealSetsPrice = mealSetList.stream()
                .map(MealSet::getTotalPrice)
                .reduce(0.0f, Float::sum);
        Float customOrderItemsPrice = orderItemList.stream()
                .map(OrderItem::getPriceAtOrder)
                .reduce(0.0f, Float::sum);


        Order order = orderRepository.save(ORDER_MAPPER.mapToEntity(createOrderRequest, mealSetsPrice, customOrderItemsPrice));

        mealSetList.forEach(mealSet -> mealSet.setOrder(order));
        orderItemList.forEach(orderItem -> orderItem.setOrder(order));
        orderItemRepository.saveAll(orderItemList);
        mealSetRepository.saveAll(mealSetList);


        UserResponse userResponse = userClient.getUserByUsername(username).getBody();

        if(order.getOrderStatus().equals(OrderStatus.COMPLETED)){
            orderProducer.sendOrderConfirmation(
                    OrderConfirmationEvent.builder()
                            .customerId(userResponse.getId())
                            .customerName(userResponse.getFirstName())
                            .customerMail(userResponse.getEmail())
                            .totalAmount(mealSetsPrice+customOrderItemsPrice)
                            .build());
        }



        return ORDER_CREATED.getMessage();
    }

    @Override
    public String updateOrder(Long orderId, UpdateOrderRequest updateOrderRequest) {
        Order orderToUpdate = orderRepository.findById(orderId)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                ORDER_NOT_FOUND.getMessage(),
                                orderId
                        )
                ));

        Order updatedOrder = ORDER_MAPPER.mapToEntityToUpdate(orderToUpdate, updateOrderRequest);
        orderRepository.save(updatedOrder);
        return ORDER_UPDATED.getMessage();
    }

    @Override
    public String deleteOrder(Long orderId) {
        Order orderToDelete = orderRepository.findById(orderId)
                        .orElseThrow(()-> new NotFoundException(
                                format(
                                        ORDER_NOT_FOUND.getMessage(),
                                        orderId
                                )
                        ));

        orderRepository.delete(orderToDelete);
        return ORDER_DELETED.getMessage();
    }

    @Override
    public GetOrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                ORDER_NOT_FOUND.getMessage(),
                                orderId
                        )
                ));

        return ORDER_MAPPER.mapOrderToResponse(order);
    }

    @Override
    public List<GetOrderResponse> getOrders() {
        return orderRepository.findAll().stream()
                .map(ORDER_MAPPER::mapOrderToResponse)
                .toList();
    }
}
