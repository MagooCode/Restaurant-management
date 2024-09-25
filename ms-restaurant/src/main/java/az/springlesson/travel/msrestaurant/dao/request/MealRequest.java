package az.springlesson.travel.msrestaurant.dao.request;

import az.springlesson.travel.msrestaurant.entity.Details;
import az.springlesson.travel.msrestaurant.enums.Category;
import az.springlesson.travel.msrestaurant.validations.ValidUrl;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MealRequest {
    @NotBlank(message = "Meal's name must be written")
    private String mealName;
    @NotBlank(message = "Price must be written")
    private Float mealPrice;
    @NotBlank(message = "Category must be written")
    private Category category;
    @ValidUrl
    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    private String imageUrl;
    @Embedded
    @NotBlank(message = "Description can't be unwritten")
    private Details details;

}
