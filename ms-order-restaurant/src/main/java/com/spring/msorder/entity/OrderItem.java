package com.spring.msorder.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.msorder.enums.OrderItemType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @SequenceGenerator(name = "order_item_id", sequenceName = "order_item_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_id")
    private Long id;

    private Long mealId;

    @ManyToOne()
    @JoinColumn(name = "meal_set_id")
    private MealSet mealSet;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(unique=true, nullable=false)
    private Byte quantity;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private OrderItemType orderItemType;

    @Column(nullable = false)
    private Float priceAtOrder;


}
