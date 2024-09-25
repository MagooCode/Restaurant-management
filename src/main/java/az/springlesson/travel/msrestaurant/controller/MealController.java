package az.springlesson.travel.msrestaurant.controller;

import az.springlesson.travel.msrestaurant.dao.request.MealRequest;
import az.springlesson.travel.msrestaurant.dao.response.MealResponse;
import az.springlesson.travel.msrestaurant.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/meal/")
public class MealController {

    private final MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping()
    public ResponseEntity<List<MealResponse>> getAllMeals(){
        return ResponseEntity.ok(mealService.getAllMeals());
    }

    @GetMapping("{id}")
    public ResponseEntity<MealResponse> getMealById(@PathVariable Long id){
        return ResponseEntity.ok(mealService.getMealById(id));
    }

    @PostMapping()
    public ResponseEntity<String> createMeal(@RequestBody MealRequest mealRequest){
        return ResponseEntity.ok(mealService.createMeal(mealRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateMeal(@RequestBody MealRequest mealRequest,@PathVariable Long id){
        return ResponseEntity.ok(mealService.updateMealById(mealRequest,id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMeal(@PathVariable Long id) {
        return ResponseEntity.ok(mealService.deleteMealById(id));
    }



}
