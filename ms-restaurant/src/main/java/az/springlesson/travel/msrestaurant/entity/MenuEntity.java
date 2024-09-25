package az.springlesson.travel.msrestaurant.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "menus")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuEntity {
    @Id
    @SequenceGenerator(name = "menu_id", sequenceName = "menu_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_id")
    private Long menuId;

    @OneToMany(mappedBy = "menu")
    private List<MealEntity> meals;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;


}
