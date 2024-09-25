package az.springlesson.travel.msrestaurant.controller;

import az.springlesson.travel.msrestaurant.dao.request.MenuResponse;
import az.springlesson.travel.msrestaurant.dao.response.MenuRequest;
import az.springlesson.travel.msrestaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/menus/")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<MenuResponse> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("{id}")
    public MenuResponse getMenusById(@PathVariable Long id){
        return menuService.getMenuById(id);
    }

    @PostMapping
    public String addMenu(@RequestBody MenuRequest menuRequest,@RequestHeader String username){
        return menuService.addMenu(menuRequest,username);
    }

    @PutMapping ("{menuId}")
    public String updateMenu(@RequestBody MenuRequest menuRequest,@RequestHeader String username,@PathVariable Long menuId){
        return menuService.updateMenu(menuRequest,username,menuId);
    }

    @DeleteMapping("{menuId}")
    public String deleteMenu(@PathVariable Long menuId,@RequestHeader String username){
        return menuService.deleteMenu(menuId,username);
    }


}
