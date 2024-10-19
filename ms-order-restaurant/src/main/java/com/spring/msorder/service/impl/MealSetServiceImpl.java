package com.spring.msorder.service.impl;

import com.spring.msorder.client.RestaurantClient;
import com.spring.msorder.dao.Requests.MealSetRequests.CreateMealSetRequest;
import com.spring.msorder.dao.Requests.MealSetRequests.UpdateMealSetRequest;
import com.spring.msorder.dao.Responses.MealSetResponses.GetMealSetResponse;
import com.spring.msorder.dao.Responses.MenuResponse;
import com.spring.msorder.dao.Responses.RestaurantResponses.MealResponse;
import com.spring.msorder.entity.MealSet;
import com.spring.msorder.entity.Order;
import com.spring.msorder.exception.InsufficientTotalPriceException;
import com.spring.msorder.exception.NotFoundException;
import com.spring.msorder.repository.MealSetRepository;
import com.spring.msorder.repository.OrderRepository;
import com.spring.msorder.service.MealSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.spring.msorder.enums.ErrorMessages.MEAL_SET_NOT_FOUND;
import static com.spring.msorder.enums.ErrorMessages.ORDER_NOT_FOUND;
import static com.spring.msorder.enums.InfoMessages.*;
import static com.spring.msorder.mapper.MealSetMapper.*;
import static java.lang.String.format;

@Service
public class MealSetServiceImpl implements MealSetService {
    private final MealSetRepository mealSetRepository;
    private final OrderRepository orderRepository;
    private final RestaurantClient restaurantClient;

    @Autowired
    public MealSetServiceImpl(MealSetRepository mealSetRepository, OrderRepository orderRepository, RestaurantClient restaurantClient) {
        this.mealSetRepository = mealSetRepository;
        this.orderRepository = orderRepository;
        this.restaurantClient = restaurantClient;
    }

    @Override
    public String saveMealSet(CreateMealSetRequest createMealSetRequest) {
        MenuResponse menuResponse = restaurantClient.getMenusById(createMealSetRequest.getMenuId());
        MealSet mealSet = MEAL_SET_MAPPER.mapToEntity(createMealSetRequest, menuResponse);
        mealSetRepository.save(mealSet);
        return MEAL_SET_SAVED.getMessage();
    }

    @Override
    public String updateMealSet(Long mealSetId, UpdateMealSetRequest updateMealSetRequest) {
        MenuResponse menuResponse = restaurantClient.getMenusById(updateMealSetRequest.getMenuId());
        MealSet mealSetToUpdate = findMealSetById(mealSetId);
        MealSet updatedMealSet = MEAL_SET_MAPPER.mapToEntityToUpdate(mealSetToUpdate, updateMealSetRequest, menuResponse);
        mealSetRepository.save(updatedMealSet);
        return MEAL_SET_UPDATED.getMessage();
    }

    @Override
    public String deleteMealSet(Long mealSetId) {
        MealSet mealSetToDelete = findMealSetById(mealSetId);
        mealSetRepository.delete(mealSetToDelete);
        return MEAL_SET_DELETED.getMessage();
    }

    @Override
    public GetMealSetResponse getMealSetById(Long mealSetId) {
        MealSet mealSet = findMealSetById(mealSetId);
        return MEAL_SET_MAPPER.mapMealSetToResponse(mealSet);
    }

    @Override
    public List<GetMealSetResponse> getMealSets() {
        return mealSetRepository.findAll().stream().map(MEAL_SET_MAPPER::mapMealSetToResponse).toList();
    }

    @Override
    public void assignMealSetToOrder(Long mealSetId, Long orderId) {
        MealSet mealSetToAssign = findMealSetById(mealSetId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                ORDER_NOT_FOUND.getMessage(),
                                orderId
                        )
                ));
        order.getMealSets().add(mealSetToAssign);

        float mealSetsPrice = 0;
        for (MealSet mealSet : order.getMealSets()) {
            mealSetsPrice += mealSet.getTotalPrice();
        }

        order.setTotalPrice(mealSetsPrice + order.getCustomOrderItemsPrice());
        orderRepository.save(order);
    }


    private MealSet findMealSetById(Long mealSetId) {
        return mealSetRepository.findById(mealSetId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                MEAL_SET_NOT_FOUND.getMessage(),
                                mealSetId
                        )
                ));
    }
}
