package com.spring.msorder.Controllers;

import com.spring.msorder.DAO.Requests.MealSetRequests.CreateMealSetRequest;
import com.spring.msorder.DAO.Requests.MealSetRequests.UpdateMealSetRequest;
import com.spring.msorder.DAO.Responses.MealSetResponses.GetMealSetResponse;
import com.spring.msorder.Service.ServiceImplementations.MealSetServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/mealSets")
public class MealSetController {
    private final MealSetServiceImplementation mealSetServiceImplementation;

    @Autowired
    public MealSetController(MealSetServiceImplementation mealSetServiceImplementation) {
        this.mealSetServiceImplementation = mealSetServiceImplementation;
    }

    @GetMapping("/{id}")
    public GetMealSetResponse getMealSetById(@PathVariable Long id){
        return mealSetServiceImplementation.getMealSetById(id);
    }

    @GetMapping()
    public List<GetMealSetResponse> getAllMealSets(){
        return mealSetServiceImplementation.getMealSets();
    }

    @PostMapping()
    public String createMealSet(@RequestBody CreateMealSetRequest createMealSetRequest){
        return mealSetServiceImplementation.saveMealSet(createMealSetRequest);
    }

    @PutMapping("{id}")
    public String updateMealSet(@RequestBody UpdateMealSetRequest updateMealSetRequest, @PathVariable Long id){
        return mealSetServiceImplementation.updateMealSet(id, updateMealSetRequest);
    }

    @DeleteMapping("{id}")
    public String deleteMealSet(@PathVariable Long id) {
        return mealSetServiceImplementation.deleteMealSet(id);
    }
}
