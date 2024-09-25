package az.springlesson.travel.msrestaurant.mapper;

import az.springlesson.travel.msrestaurant.dao.request.MenuResponse;
import az.springlesson.travel.msrestaurant.dao.response.MenuRequest;
import az.springlesson.travel.msrestaurant.entity.MealEntity;
import az.springlesson.travel.msrestaurant.entity.MenuEntity;
import az.springlesson.travel.msrestaurant.entity.RestaurantEntity;

import java.util.List;

import static az.springlesson.travel.msrestaurant.mapper.MealMapper.*;
import static az.springlesson.travel.msrestaurant.mapper.RestaurantMapper.RESTAURANT_MAPPER;

public enum MenuMapper {
    MENU_MAPPER;

    public MenuEntity mapToEntity(RestaurantEntity restaurantEntity, List<MealEntity> mealEntityList) {
        return MenuEntity.builder()
                .meals(mealEntityList)
                .restaurant(restaurantEntity)
                .build();

    }

    public MenuResponse mapToResponse(MenuEntity menuEntity) {
        return MenuResponse.builder()
                .id(menuEntity.getMenuId())
                .restaurant(RESTAURANT_MAPPER.mapToResponse(menuEntity.getRestaurant()))
                .meals(menuEntity.getMeals().stream().map(MEAL_MAPPER::mapToResponse).toList())
                .build();
    }

}
