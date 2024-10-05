package com.spring.msorder.Service;

import com.spring.msorder.DAO.Requests.MealSetRequests.CreateMealSetRequest;
import com.spring.msorder.DAO.Requests.MealSetRequests.UpdateMealSetRequest;
import com.spring.msorder.DAO.Responses.MealSetResponses.GetMealSetResponse;
import com.spring.msorder.Entity.MealSet;

import java.util.List;

public interface MealSetService {
    String saveMealSet(CreateMealSetRequest createMealSetRequest);
    String updateMealSet(Long mealSetId, UpdateMealSetRequest updateMealSetRequest);
    String deleteMealSet(Long mealSetId);
    GetMealSetResponse getMealSetById(Long mealSetId);
    List<GetMealSetResponse> getMealSets();
    void assignMealSetToOrder(Long mealSetId, Long orderId);
}
