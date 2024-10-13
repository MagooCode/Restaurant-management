package com.spring.msorder.dao.Requests.MealSetRequests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateMealSetRequest {
    @NotNull(message = "Menu id must be provided")
    private Long menuId;

    @NotNull(message = "Meal set name must be provided")
    @Min(value = 3, message = "Meal set name must be at least 3 characters long")
    @Max(value = 70, message = "Meal set name must not exceed 70 characters")
    private String name;

    @NotNull(message = "Meal set name must be provided")
    @Min(value = 3, message = "Meal set description must be at least 10 characters long")
    @Max(value = 70, message = "Meal set description must not exceed 250 characters")
    private String description;


    @NotNull(message = "Meal set discount must be provided")
    private Float discount;
}
