package com.spring.msorder.Service.ServiceImplementations;

import com.spring.msorder.DAO.Requests.MealSetRequests.CreateMealSetRequest;
import com.spring.msorder.DAO.Requests.MealSetRequests.UpdateMealSetRequest;
import com.spring.msorder.Entity.MealSet;
import com.spring.msorder.Mapper.MealSetMapper;
import com.spring.msorder.Repository.MealSetRepository;
import com.spring.msorder.Service.MealSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealSetServiceImplementation implements MealSetService {

    private final MealSetRepository mealSetRepository;

    @Autowired
    public MealSetServiceImplementation(final MealSetRepository mealSetRepository) {
        this.mealSetRepository = mealSetRepository;
    }

    @Override
    public String saveMealSet(CreateMealSetRequest createMealSetRequest) {
        MealSet mealSet = MealSetMapper.MEAL_SET_MAPPER.mapToEntity(createMealSetRequest);
        mealSetRepository.save(mealSet);
        return "Meal set saved successfully";
    }

    @Override
    public String updateMealSet(Long mealSetId, UpdateMealSetRequest updateMealSetRequest) {
        MealSet mealSetToUpdate = mealSetRepository.findById(mealSetId).get();
        MealSet updatedMealSet = MealSetMapper.MEAL_SET_MAPPER.mapToEntityToUpdate(mealSetToUpdate, updateMealSetRequest);
        mealSetRepository.save(updatedMealSet);
        return "Meal set updated successfully";
    }

    @Override
    public String deleteMealSet(Long mealSetId) {
        MealSet mealSetToDelete = mealSetRepository.findById(mealSetId).get();
        mealSetRepository.delete(mealSetToDelete);
        return "Meal set deleted successfully";
    }

    @Override
    public MealSet getMealSetById(Long mealSetId) {
        return mealSetRepository.findById(mealSetId).get();
    }

    @Override
    public List<MealSet> getMealSets() {
        List<MealSet> mealSets = mealSetRepository.findAll();
        return mealSets;
    }
}
