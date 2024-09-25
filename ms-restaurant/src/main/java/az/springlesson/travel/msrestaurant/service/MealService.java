package az.springlesson.travel.msrestaurant.service;

import az.springlesson.travel.msrestaurant.dao.request.MealRequest;
import az.springlesson.travel.msrestaurant.dao.response.MealResponse;

import java.util.List;

public interface MealService {

    List<MealResponse> getAllMeals();

    MealResponse getMealById(Long id);

    String createMeal(MealRequest mealRequest);

    String updateMealById(MealRequest mealRequest,Long id);

    String deleteMealById(Long id);
}
