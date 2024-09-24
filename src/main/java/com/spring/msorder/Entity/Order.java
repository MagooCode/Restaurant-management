package com.spring.msorder.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.msorder.Enum.OrderStatus;
import com.spring.msorder.Enum.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @SequenceGenerator(name = "order_id", sequenceName = "order_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id")
    private Long id;

    private Long restaurantId;

    @OneToMany(mappedBy = "order")
    @JsonBackReference
    private List<OrderItem> orderItems;

    @Column(unique=true, nullable=false)
    private Float totalPrice;

    @Column(nullable=false)
    private OrderStatus orderStatus;

    @Column(nullable=false)
    private PaymentStatus paymentStatus;

    @Column(unique=true, nullable=false)
    private Float discount;

    @Column(nullable=false)
    private String deliveryAddress;

    @Column(nullable=false)
    private LocalDateTime deliveryTime;
}
