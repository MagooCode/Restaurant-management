package com.spring.msorder.Mapper;

import com.spring.msorder.DAO.Requests.MealSetRequests.CreateMealSetRequest;
import com.spring.msorder.DAO.Requests.MealSetRequests.UpdateMealSetRequest;
import com.spring.msorder.Entity.MealSet;
import com.spring.msorder.Entity.OrderItem;

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
}
