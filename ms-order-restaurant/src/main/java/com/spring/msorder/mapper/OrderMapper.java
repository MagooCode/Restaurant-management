package com.spring.msorder.mapper;

import com.spring.msorder.dao.Requests.OrderRequests.CreateOrderRequest;
import com.spring.msorder.dao.Requests.OrderRequests.UpdateOrderRequest;
import com.spring.msorder.dao.Responses.OrderResponses.GetOrderResponse;
import com.spring.msorder.entity.MealSet;
import com.spring.msorder.entity.Order;
import com.spring.msorder.entity.OrderItem;
import com.spring.msorder.enums.OrderStatus;
import com.spring.msorder.enums.PaymentStatus;

import java.util.List;

import static com.spring.msorder.enums.OrderStatus.*;

public enum OrderMapper {
    ORDER_MAPPER;

    public Order mapToEntity(CreateOrderRequest createOrderRequest, List<MealSet> mealSetList ,
                             List<OrderItem> orderItemList,Long customerId) {

        Float mealSetPrice = calculateMealSetPrice(mealSetList);
        Float customOrderItemPrice = calculateOrderItemPrice(orderItemList);

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
                .customerId(customerId)
                .build();
    }

    public Order mapToEntityToUpdate(Order orderToUpdate, UpdateOrderRequest updateOrderRequest,
                                     List<MealSet> mealSetList , List<OrderItem> orderItemList) {

        Float mealSetPrice = calculateMealSetPrice(mealSetList);
        Float customOrderItemPrice = calculateOrderItemPrice(orderItemList);

        orderToUpdate.setRestaurantId(updateOrderRequest.getRestaurantId());
        orderToUpdate.setDeliveryAddress(updateOrderRequest.getDeliveryAddress());
        orderToUpdate.setDeliveryTime(updateOrderRequest.getDeliveryTime());
        orderToUpdate.setDiscount(updateOrderRequest.getDiscount());
        orderToUpdate.setMealSetsPrice(mealSetPrice);
        orderToUpdate.setCustomOrderItemsPrice(customOrderItemPrice);


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

    private Float calculateMealSetPrice(List<MealSet> mealSetList) {
        return mealSetList.stream()
                .map(MealSet::getTotalPrice)
                .reduce(0.0f, Float::sum);
    }

    private Float calculateOrderItemPrice(List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(OrderItem::getPriceAtOrder)
                .reduce(0.0f, Float::sum);
    }
}
