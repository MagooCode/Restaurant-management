package com.spring.msorder.mapper;

import com.spring.msorder.dao.Requests.OrderRequests.CreateOrderRequest;
import com.spring.msorder.dao.Requests.OrderRequests.UpdateOrderRequest;
import com.spring.msorder.dao.Responses.OrderResponses.GetOrderResponse;
import com.spring.msorder.entity.Order;
import com.spring.msorder.enums.OrderStatus;
import com.spring.msorder.enums.PaymentStatus;

import static com.spring.msorder.enums.OrderStatus.*;

public enum OrderMapper {
    ORDER_MAPPER;

    public Order mapToEntity(CreateOrderRequest createOrderRequest,Float mealSetPrice , Float customOrderItemPrice) {
        return Order.builder()
                .discount(createOrderRequest.getDiscount())
                .deliveryAddress(createOrderRequest.getDeliveryAddress())
                .orderStatus(COMPLETED)
                .mealSetsPrice(mealSetPrice)
                .paymentStatus(PaymentStatus.PENDING)
                .customOrderItemsPrice(customOrderItemPrice)
                .totalPrice(customOrderItemPrice+mealSetPrice)
                .deliveryTime(createOrderRequest.getDeliveryTime())
                .restaurantId(createOrderRequest.getRestaurantId())
                .build();
    }

    public Order mapToEntityToUpdate(Order orderToUpdate, UpdateOrderRequest updateOrderRequest) {
        if (updateOrderRequest.getRestaurantId() != null) {
            orderToUpdate.setRestaurantId(updateOrderRequest.getRestaurantId());
        }

        if (updateOrderRequest.getDeliveryAddress() != null) {
            orderToUpdate.setDeliveryAddress(updateOrderRequest.getDeliveryAddress());
        }

        if (updateOrderRequest.getDeliveryTime() != null) {
            orderToUpdate.setDeliveryTime(updateOrderRequest.getDeliveryTime());
        }

        if (updateOrderRequest.getDiscount() != null) {
            orderToUpdate.setDiscount(updateOrderRequest.getDiscount());
        }

        return orderToUpdate;
    }

    public GetOrderResponse mapOrderToResponse(Order order) {
        return GetOrderResponse.builder()
                .id(order.getId())
                .restaurantId(order.getRestaurantId())
                .totalPrice(order.getTotalPrice())
                .customOrderItemsPrice(order.getCustomOrderItemsPrice())
                .mealSetsPrice(order.getMealSetsPrice())
                .orderStatus(order.getOrderStatus())
                .paymentStatus(order.getPaymentStatus())
                .discount(order.getDiscount())
                .deliveryAddress(order.getDeliveryAddress())
                .deliveryTime(order.getDeliveryTime())
                .build();
    }
}
