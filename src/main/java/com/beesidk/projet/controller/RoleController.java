package com.beesidk.projet.controller;


import com.beesidk.projet.entity.AppRole;
import com.beesidk.projet.entity.AppRole;
import com.beesidk.projet.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private IService<AppRole> AppRoleService;


    // http://localhost:8089/projet/AppRole/retrieve-all-AppRoles
    @Operation(description = "Ce web service permet de récupérer toutes les AppRoles de la base de données")
    @GetMapping("/retrieve-all-AppRoles")
    public List<AppRole> getAppRoles() {
        List<AppRole> listAppRoles = AppRoleService.retrieveAll();
        return listAppRoles;
    }

    // http://localhost:8089/projet/AppRole/retrieve-AppRole/8
    @GetMapping("/retrieve-AppRole/{AppRole-id}")
    public AppRole retrieveAppRole(@PathVariable("AppRole-id") String id) {
        AppRole AppRole = AppRoleService.retrieve(id);
        return AppRole;
    }

    // http://localhost:8089/projet/AppRole/add-AppRole
    @PostMapping("/add-AppRole")
    public AppRole addAppRole(@RequestBody AppRole c) {
        AppRole AppRole = AppRoleService.add(c);
        return AppRole;
    }

    // http://localhost:8089/projet/AppRole/remove-AppRole/{AppRole-id}
    @DeleteMapping("/remove-AppRole/{AppRole-id}")
    public void removeAppRole(@PathVariable("AppRole-id") String id) {
        AppRoleService.remove(id);
    }

    // http://localhost:8089/projet/AppRole/modify-AppRole
    @PutMapping("/modify-AppRole")
    public AppRole modifyAppRole(@RequestBody AppRole c) {
        AppRole AppRole = AppRoleService.modify(c);
        return AppRole;
    }
}
