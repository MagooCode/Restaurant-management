package com.spring.msorder.service;

import com.spring.msorder.dao.Requests.MealSetRequests.CreateMealSetRequest;
import com.spring.msorder.dao.Requests.MealSetRequests.UpdateMealSetRequest;
import com.spring.msorder.dao.Responses.MealSetResponses.GetMealSetResponse;

import java.util.List;

public interface MealSetService {
    String saveMealSet(CreateMealSetRequest createMealSetRequest);
    String updateMealSet(Long mealSetId, UpdateMealSetRequest updateMealSetRequest);
    String deleteMealSet(Long mealSetId);
    GetMealSetResponse getMealSetById(Long mealSetId);
    List<GetMealSetResponse> getMealSets();
    void assignMealSetToOrder(Long mealSetId, Long orderId);
}
