package com.spring.msorder.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "meal_sets")
public class MealSet {
    @Id
    @SequenceGenerator(name = "meal_set_id", sequenceName = "meal_set_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meal_set_id")
    private Long id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "order_id")
    private Order order;

    private Long menuId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @Column(unique=true, nullable=false)
    private String name;

    @Column(unique=true, nullable=false)
    private String description;

    @Column(nullable=false)
    private Float discount;

    @Column(nullable=false)
    private Float totalPrice;

}
