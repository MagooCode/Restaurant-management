package az.springlesson.travel.msrestaurant.service;

import az.springlesson.travel.msrestaurant.dao.response.MenuResponse;
import az.springlesson.travel.msrestaurant.dao.request.MenuRequest;

import java.util.List;

public interface MenuService {
    List<MenuResponse> getAllMenus();

    MenuResponse getMenuById(Long id);

    String addMenu(MenuRequest menuRequest);

    String updateMenu(MenuRequest menuRequest, String username,Long menuId);

    String deleteMenu(Long id, String username);
}
