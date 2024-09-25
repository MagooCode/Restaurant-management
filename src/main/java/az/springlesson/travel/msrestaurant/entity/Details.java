package az.springlesson.travel.msrestaurant.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "details")
public class Details {
    @Id
    @SequenceGenerator(name = "details_id" , sequenceName = "details_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "details_id")
    private Long id;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private MealEntity mealEntity;

    @OneToOne(cascade = CascadeType.ALL)
    private RestaurantEntity restaurantEntity;

}
