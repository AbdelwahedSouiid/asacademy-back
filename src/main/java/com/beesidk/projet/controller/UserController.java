package com.beesidk.projet.controller;


import com.beesidk.projet.entity.AppUser;
import com.beesidk.projet.interfaces.IService;
import com.beesidk.projet.service.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/AppUser")
public class UserController {


    AppUserService AppUserService;

    // http://localhost:8089/projet/AppUser/retrieve-all-AppUsers
    @Operation(description = "Ce web service permet de récupérer toutes les AppUsers de la base de données")
    @GetMapping("/retrieve-all-Users")
    public List<AppUser> getAppUsers() {
        List<AppUser> listAppUsers = AppUserService.retrieveAll();
        return listAppUsers;
    }

    // http://localhost:8089/projet/AppUser/retrieve-AppUser/8
    @GetMapping("/retrieve-AppUser/{id}")
    public AppUser retrieveAppUser(@PathVariable("id") String id) {
        AppUser AppUser = AppUserService.retrieve(id);
        return AppUser;
    }

    // http://localhost:8089/projet/AppUser/add-AppUser
    @PostMapping("/add-AppUser")
    public AppUser addAppUser(@RequestBody AppUser c, MultipartFile mf) throws IOException {
        AppUser AppUser = AppUserService.add(c);

        return AppUser;
    }

    // http://localhost:8089/projet/AppUser/remove-AppUser/{AppUser-id}
    @DeleteMapping("/remove-AppUser/{id}")
    public void removeAppUser(@PathVariable("id") String id) {
        AppUserService.remove(id);
    }

    // http://localhost:8089/projet/AppUser/modify-AppUser
    @PutMapping("/modify-AppUser")
    public AppUser modifyAppUser(@RequestBody AppUser c) {
        AppUser AppUser = AppUserService.modify(c);
        return AppUser;
    }

    @GetMapping("/retrieve-AppUser-BY-Email/{user-email}")
    public AppUser retrieveAppUserByEmail(@PathVariable("user-email") String userEmail) {
        AppUser AppUser = AppUserService.retrieveByEmail(userEmail);
        return AppUser;
    }

}
