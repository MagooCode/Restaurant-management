package com.spring.msorder.mapper;

import com.spring.msorder.dao.Requests.MealSetRequests.CreateMealSetRequest;
import com.spring.msorder.dao.Requests.MealSetRequests.UpdateMealSetRequest;
import com.spring.msorder.dao.Responses.MealSetResponses.GetMealSetResponse;
import com.spring.msorder.entity.MealSet;

public enum MealSetMapper {
    MEAL_SET_MAPPER;

    public MealSet mapToEntity(CreateMealSetRequest createMealSetRequest) {
        return MealSet.builder()
                .menuId(createMealSetRequest.getMenuId())
                .name(createMealSetRequest.getName())
                .description(createMealSetRequest.getDescription())
                .discount(createMealSetRequest.getDiscount())
                .build();
    }

    public MealSet mapToEntityToUpdate(MealSet mealSetToUpdate, UpdateMealSetRequest updateMealSetRequest) {
        if(updateMealSetRequest.getName() != null) {
            mealSetToUpdate.setName(updateMealSetRequest.getName());
        }

        if(updateMealSetRequest.getMenuId() != null) {
            mealSetToUpdate.setMenuId(updateMealSetRequest.getMenuId());
        }

        if(updateMealSetRequest.getDescription() != null) {
            mealSetToUpdate.setDescription(updateMealSetRequest.getDescription());
        }

        if(updateMealSetRequest.getDiscount() != null) {
            mealSetToUpdate.setDiscount(updateMealSetRequest.getDiscount());
        }

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
