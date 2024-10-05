package com.spring.msorder.DAO.Responses.MealSetResponses;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetMealSetResponse {
    private Long id;

    private Long menuId;

    private String name;

    private String description;

    private Float discount;

    private Float totalPrice;
}
