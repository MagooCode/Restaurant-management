package az.springlesson.travel.msrestaurant.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "restaurants")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantEntity {
    @Id
    @SequenceGenerator(name = "restaurant_id", sequenceName = "restaurant_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_id")
    private Long restaurantId;
    private Long ownerId;

    @Column(nullable = false, unique = true)
    private String restaurantName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private Details details;

    @Column(nullable = false)
    private String restaurantAddress;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @DecimalMin(value = "0.0", inclusive = false, message = "Rating must be greater than 0")
    @DecimalMax(value = "9.0", inclusive = true, message = "Rating must be less than or equal to 9")
    private Float restaurantRating;


    @OneToMany(mappedBy = "restaurant")
    private List<MenuEntity> menu;

    @OneToMany(mappedBy = "restaurant")
    private List<ReviewEntity> reviews;

}
