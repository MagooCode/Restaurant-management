package az.springlesson.travel.msrestaurant.service.impl;

import az.springlesson.travel.msrestaurant.client.UserServiceClient;
import az.springlesson.travel.msrestaurant.dao.response.MenuResponse;
import az.springlesson.travel.msrestaurant.dao.request.MenuRequest;
import az.springlesson.travel.msrestaurant.dao.response.UserResponse;
import az.springlesson.travel.msrestaurant.entity.MealEntity;
import az.springlesson.travel.msrestaurant.entity.MenuEntity;
import az.springlesson.travel.msrestaurant.entity.RestaurantEntity;
import az.springlesson.travel.msrestaurant.exceptions.NotFoundException;
import az.springlesson.travel.msrestaurant.exceptions.UnauthorizedException;
import az.springlesson.travel.msrestaurant.repository.MealRepository;
import az.springlesson.travel.msrestaurant.repository.MenuRepository;
import az.springlesson.travel.msrestaurant.repository.RestaurantRepository;
import az.springlesson.travel.msrestaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.springlesson.travel.msrestaurant.enums.ErrorMessages.*;
import static az.springlesson.travel.msrestaurant.enums.InfoMessage.*;
import static az.springlesson.travel.msrestaurant.mapper.MenuMapper.*;
import static java.lang.String.format;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final MealRepository mealRepository;
    private final UserServiceClient userServiceClient;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository, RestaurantRepository restaurantRepository, MealRepository mealRepository, UserServiceClient userServiceClient) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
        this.mealRepository = mealRepository;
        this.userServiceClient = userServiceClient;
    }


    @Override
    public List<MenuResponse> getAllMenus() {
        return menuRepository.findAll().stream()
                .map(MENU_MAPPER::mapToResponse)
                .toList();
    }

    @Override
    public MenuResponse getMenuById(Long id) {
        return menuRepository.findById(id)
                .map(MENU_MAPPER::mapToResponse)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                MENU_NOT_FOUND.getMessage(),
                                id
                        )
                ));
    }

    @Override
    public String addMenu(MenuRequest menuRequest) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(menuRequest.getRestaurantId())
                .orElseThrow(()-> new NotFoundException(
                        format(
                                RESTAURANT_NOT_FOUND.getMessage(),
                                menuRequest.getRestaurantId()
                        )
                ));

        List<MealEntity> mealEntityList = mealRepository.findAllById(menuRequest.getMealIds());

        MenuEntity menuEntity= menuRepository.save(MENU_MAPPER.mapToEntity(restaurantEntity));

        mealEntityList.forEach(mealEntity -> mealEntity.setMenu(menuEntity));
        mealRepository.saveAll(mealEntityList);

        return MENU_CREATED.getMessage();
    }

    @Override
    public String updateMenu(MenuRequest menuRequest, String username, Long menuId) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(menuRequest.getRestaurantId())
                .orElseThrow(()-> new NotFoundException(
                        format(
                                MENU_NOT_FOUND.getMessage(),
                                menuId
                        )
                ));

        if(!hasPermission(restaurantEntity,username)){
            throw new UnauthorizedException(ACTION_UNAUTHORIZED.getMessage());
        }

        MenuEntity menuEntity = menuRepository.findById(menuId)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                MENU_NOT_FOUND.getMessage()
                        )
                ));

        menuEntity.setMeals(mealRepository.findAllById(menuRequest.getMealIds()));
        menuRepository.save(menuEntity);

        return MENU_UPDATED.getMessage();


    }

    @Override
    public String deleteMenu(Long id, String username) {
        MenuEntity menuEntity = menuRepository.findById(id)
                        .orElseThrow(()-> new NotFoundException(
                                format(
                                        MENU_NOT_FOUND.getMessage(),
                                        id
                                )
                        ));

        RestaurantEntity restaurantEntity = menuEntity.getRestaurant();
        if(!hasPermission(restaurantEntity,username)){
            throw new UnauthorizedException(ACTION_UNAUTHORIZED.getMessage());
        }

        menuRepository.deleteById(id);

        return MENU_DELETED.getMessage();

    }

    private Boolean hasPermission(RestaurantEntity restaurantEntity, String username){
        UserResponse userResponse = userServiceClient.getUserByUsername(username).getBody();
        return restaurantEntity.getOwnerId().equals(userResponse.getId()) || userResponse.getRoleName().equals("ADMIN");
    }
}
