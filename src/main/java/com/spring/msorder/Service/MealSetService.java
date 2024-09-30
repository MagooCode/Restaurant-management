package com.spring.msorder.Service;

import com.spring.msorder.DAO.Requests.MealSetRequests.CreateMealSetRequest;
import com.spring.msorder.DAO.Requests.MealSetRequests.UpdateMealSetRequest;
import com.spring.msorder.Entity.MealSet;

import java.util.List;

public interface MealSetService {
    String saveMealSet(CreateMealSetRequest createMealSetRequest);
    String updateMealSet(Long mealSetId, UpdateMealSetRequest updateMealSetRequest);
    String deleteMealSet(Long mealSetId);
    MealSet getMealSetById(Long mealSetId);
    List<MealSet> getMealSets();
}
