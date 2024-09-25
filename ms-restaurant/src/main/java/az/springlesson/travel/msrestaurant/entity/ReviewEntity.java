package az.springlesson.travel.msrestaurant.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "reviews")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEntity {
    @Id
    @SequenceGenerator(name = "review_id", sequenceName = "review_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_id")
    private Long id;
    private String reviewMessage;
    private Long userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;


}
