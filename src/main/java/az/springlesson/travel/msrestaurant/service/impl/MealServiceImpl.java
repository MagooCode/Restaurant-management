package az.springlesson.travel.msrestaurant.service.impl;

import az.springlesson.travel.msrestaurant.dao.request.MealRequest;
import az.springlesson.travel.msrestaurant.dao.response.MealResponse;
import az.springlesson.travel.msrestaurant.entity.MealEntity;
import az.springlesson.travel.msrestaurant.exceptions.NotFoundException;
import az.springlesson.travel.msrestaurant.mapper.MealMapper;
import az.springlesson.travel.msrestaurant.repository.MealRepository;
import az.springlesson.travel.msrestaurant.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.springlesson.travel.msrestaurant.enums.ErrorMessages.MEAL_NOT_FOUND;
import static az.springlesson.travel.msrestaurant.enums.InfoMessage.*;
import static az.springlesson.travel.msrestaurant.mapper.MealMapper.*;
import static java.lang.String.format;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }


    @Override
    public List<MealResponse> getAllMeals() {
        return mealRepository.findAll().stream()
                .map(MEAL_MAPPER::mapToResponse)
                .toList();
    }

    @Override
    public MealResponse getMealById(Long id) {
        return mealRepository.findById(id)
                .map(MEAL_MAPPER::mapToResponse)
                .orElseThrow(()-> new NotFoundException(
                        format(
                              MEAL_NOT_FOUND.getMessage(),
                                id

                        )
                ));
    }

    @Override
    public String createMeal(MealRequest mealRequest) {
        mealRepository.save(MEAL_MAPPER.mapToEntity(mealRequest));

        return MEAL_CREATED.getMessage();
    }

    @Override
    public String updateMealById(MealRequest mealRequest,Long id) {
        MealEntity mealEntity = mealRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                MEAL_NOT_FOUND.getMessage(),
                                id
                        )
                ));

        mealEntity.setMealName(mealRequest.getMealName());
        mealEntity.setMealPrice(mealRequest.getMealPrice());
        mealEntity.setCategory(mealRequest.getCategory());
        mealEntity.setImageUrl(mealRequest.getImageUrl());
        mealEntity.setDetails(mealRequest.getDetails());

        mealRepository.save(mealEntity);

        return MEAL_UPDATED.getMessage();

    }

    @Override
    public String deleteMealById(Long id) {
        MealEntity mealEntity = mealRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                MEAL_NOT_FOUND.getMessage(),
                                id
                        )
                ));

        mealRepository.delete(mealEntity);

        return MEAL_DELETED.getMessage();
    }


}
