package az.springlesson.fooddelivery.msuserservice.controller;

import az.springlesson.fooddelivery.msuserservice.dao.request.RoleRequest;
import az.springlesson.fooddelivery.msuserservice.dao.response.RoleResponse;
import az.springlesson.fooddelivery.msuserservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/role/")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getListOfRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PostMapping
    public ResponseEntity<String> createRole(@RequestBody RoleRequest roleRequest){
        return ResponseEntity.ok(roleService.createRole(roleRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateRole(@PathVariable Long id, @RequestBody RoleRequest roleRequest){
        return ResponseEntity.ok(roleService.updateRole(id, roleRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id){
        return ResponseEntity.ok(roleService.deleteRole(id));
    }

}
