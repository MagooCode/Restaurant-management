package com.spring.msorder.controller;

import com.spring.msorder.dao.Requests.MealSetRequests.CreateMealSetRequest;
import com.spring.msorder.dao.Requests.MealSetRequests.UpdateMealSetRequest;
import com.spring.msorder.dao.Responses.MealSetResponses.GetMealSetResponse;
import com.spring.msorder.service.MealSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/mealSet/")
public class MealSetController {
    private final MealSetService mealSetService;

    @Autowired
    public MealSetController(MealSetService mealSetService) {
        this.mealSetService = mealSetService;
    }

    @GetMapping("/{id}")
    public GetMealSetResponse getMealSetById(@PathVariable Long id){
        return mealSetService.getMealSetById(id);
    }

    @GetMapping()
    public List<GetMealSetResponse> getAllMealSets(){
        return mealSetService.getMealSets();
    }

    @PostMapping()
    public String createMealSet(@RequestBody CreateMealSetRequest createMealSetRequest){
        return mealSetService.saveMealSet(createMealSetRequest);
    }

    @PutMapping("{id}")
    public String updateMealSet(@RequestBody UpdateMealSetRequest updateMealSetRequest, @PathVariable Long id){
        return mealSetService.updateMealSet(id, updateMealSetRequest);
    }

    @DeleteMapping("{id}")
    public String deleteMealSet(@PathVariable Long id) {
        return mealSetService.deleteMealSet(id);
    }
}
