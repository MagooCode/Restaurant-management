package az.springlesson.travel.msrestaurant.dao.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {
    private Long id;
    private String reviewMessage;
}
