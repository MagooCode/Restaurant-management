package com.spring.msorder.Service.ServiceImplementations;

import com.spring.msorder.DAO.Requests.OrderRequests.CreateOrderRequest;
import com.spring.msorder.DAO.Requests.OrderRequests.UpdateOrderRequest;
import com.spring.msorder.Entity.Order;
import com.spring.msorder.Mapper.OrderMapper;
import com.spring.msorder.Repository.OrderRepository;
import com.spring.msorder.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImplementation(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public String createOrder(CreateOrderRequest createOrderRequest) {
        Order order = OrderMapper.ORDER_MAPPER.mapToEntity(createOrderRequest);
        orderRepository.save(order);
        return "Order successfully created";
    }

    @Override
    public String updateOrder(Long orderId, UpdateOrderRequest updateOrderRequest) {
        Order orderToUpdate = orderRepository.findById(orderId).get();
        Order updatedOrder = OrderMapper.ORDER_MAPPER.mapToEntityToUpdate(orderToUpdate, updateOrderRequest);
        orderRepository.save(updatedOrder);
        return "Order successfully updated";
    }

    @Override
    public String deleteOrder(Long orderId) {
        Order orderToDelete = orderRepository.findById(orderId).get();
        orderRepository.delete(orderToDelete);
        return "Order successfully deleted";
    }

    @Override
    public Order getOrderbyId(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        return order;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }
}
