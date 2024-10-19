package com.spring.msorder.mapper;

import com.spring.msorder.dao.Requests.MealSetRequests.CreateMealSetRequest;
import com.spring.msorder.dao.Requests.MealSetRequests.UpdateMealSetRequest;
import com.spring.msorder.dao.Responses.MealSetResponses.GetMealSetResponse;
import com.spring.msorder.dao.Responses.MenuResponse;
import com.spring.msorder.dao.Responses.RestaurantResponses.MealResponse;
import com.spring.msorder.entity.MealSet;
import com.spring.msorder.exception.InsufficientTotalPriceException;

import static com.spring.msorder.enums.ErrorMessages.ENTER_DISCOUNT_TOTAL_PRICE_CORRECT;

public enum MealSetMapper {
    MEAL_SET_MAPPER;

    public MealSet mapToEntity(CreateMealSetRequest createMealSetRequest, MenuResponse menuResponse) {
        Float totalPrice = menuResponse.getMeals().stream()
                .map(MealResponse::getMealPrice)
                .reduce(0.0f, Float::sum)-
                createMealSetRequest.getDiscount();

        if(totalPrice<0 || createMealSetRequest.getDiscount()<0){
            throw new InsufficientTotalPriceException(ENTER_DISCOUNT_TOTAL_PRICE_CORRECT.getMessage());
        }

        return MealSet.builder()
                .menuId(createMealSetRequest.getMenuId())
                .name(createMealSetRequest.getName())
                .description(createMealSetRequest.getDescription())
                .discount(createMealSetRequest.getDiscount())
                .totalPrice(totalPrice)
                .build();
    }

    public MealSet mapToEntityToUpdate(MealSet mealSetToUpdate, UpdateMealSetRequest updateMealSetRequest,MenuResponse menuResponse) {
        Float totalPrice = menuResponse.getMeals().stream()
                .map(MealResponse::getMealPrice)
                .reduce(0.0f, Float::sum)-
                updateMealSetRequest.getDiscount();

        if(totalPrice<0 || updateMealSetRequest.getDiscount()<0){
            throw new InsufficientTotalPriceException(ENTER_DISCOUNT_TOTAL_PRICE_CORRECT.getMessage());
        }

        mealSetToUpdate.setName(updateMealSetRequest.getName());
        mealSetToUpdate.setMenuId(updateMealSetRequest.getMenuId());
        mealSetToUpdate.setDescription(updateMealSetRequest.getDescription());
        mealSetToUpdate.setDiscount(updateMealSetRequest.getDiscount());

        return mealSetToUpdate;
    }

    public GetMealSetResponse mapMealSetToResponse(MealSet mealSet) {
        return GetMealSetResponse.builder()
                .id(mealSet.getId())
                .menuId(mealSet.getMenuId())
                .name(mealSet.getName())
                .description(mealSet.getDescription())
                .discount(mealSet.getDiscount())
                .totalPrice(mealSet.getTotalPrice())
                .build();
    }

}
