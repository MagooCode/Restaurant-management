package az.springlesson.travel.msrestaurant.entity;

import az.springlesson.travel.msrestaurant.enums.Category;
import az.springlesson.travel.msrestaurant.validations.ValidUrl;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "meals")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealEntity {
    @Id
    @SequenceGenerator(name = "meal_id", sequenceName = "meal_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meal_id")
    private Long id;
    private String mealName;
    private Float mealPrice;
    private Category category;
    private String imageUrl;
    private Long orderItemId;

    @OneToOne(cascade = CascadeType.ALL)
    private Details details;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;
}
