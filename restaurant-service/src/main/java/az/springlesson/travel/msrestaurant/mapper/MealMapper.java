package az.springlesson.travel.msrestaurant.mapper;

import az.springlesson.travel.msrestaurant.dao.request.MealRequest;
import az.springlesson.travel.msrestaurant.dao.response.MealResponse;
import az.springlesson.travel.msrestaurant.entity.MealEntity;

public enum MealMapper {
    MEAL_MAPPER;

    public MealEntity mapToEntity(MealRequest mealRequest) {
        return MealEntity.builder()
                .mealName(mealRequest.getMealName())
                .mealPrice(mealRequest.getMealPrice())
                .category(mealRequest.getCategory())
                .description(mealRequest.getDescription())
                .imageUrl(mealRequest.getImageUrl()).build();
    }

    public MealResponse mapToResponse(MealEntity mealEntity) {
        return MealResponse.builder()
                .id(mealEntity.getId())
                .mealName(mealEntity.getMealName())
                .mealPrice(mealEntity.getMealPrice())
                .description(mealEntity.getDescription())
                .category(String.valueOf(mealEntity.getCategory()))
                .imageUrl(mealEntity.getImageUrl()).build();
    }
}
