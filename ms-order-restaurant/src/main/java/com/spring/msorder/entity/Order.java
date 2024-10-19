package com.spring.msorder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.msorder.enums.OrderStatus;
import com.spring.msorder.enums.PaymentStatus;
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
    @SequenceGenerator(name = "order_id", sequenceName = "order_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id")
    private Long id;

    private Long restaurantId;

    private Long customerId;

    @OneToMany(mappedBy = "order")
    @JsonBackReference
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "order")
    @JsonBackReference
    private List<MealSet> mealSets;

    @Column(nullable=false)
    private Float totalPrice;

    @Column(nullable=false)
    private Float customOrderItemsPrice;

    @Column(nullable=false)
    private Float mealSetsPrice;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(nullable=false)
    private PaymentStatus paymentStatus;

    @Column(nullable=false)
    private Float discount;

    @Column(nullable=false)
    private String deliveryAddress;

    @Column(nullable=false)
    private LocalDateTime deliveryTime;
}
