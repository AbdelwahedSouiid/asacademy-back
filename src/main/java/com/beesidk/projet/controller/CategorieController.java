package com.beesidk.projet.controller;


import com.beesidk.projet.entity.Categorie;
import com.beesidk.projet.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categorie")
public class CategorieController {

    IService<Categorie> CategorieService;

    // http://localhost:8089/projet/Categorie/retrieve-all-Categories
    @Operation(description = "Ce web service permet de récupérer toutes les Categories de la base de données")
    @GetMapping("/retrieve-all-Categories")
    public List<Categorie> getCategories() {
        List<Categorie> listCategories = CategorieService.retrieveAll();
        return listCategories;
    }

    // http://localhost:8089/projet/Categorie/retrieve-Categorie/8
    @GetMapping("/retrieve-Categorie/{Categorie-id}")
    public Categorie retrieveCategorie(@PathVariable("Categorie-id") String id) {
        Categorie Categorie = CategorieService.retrieve(id);
        return Categorie;
    }

    // http://localhost:8089/projet/Categorie/add-Categorie
    @PostMapping("/add-Categorie")
    public Categorie addCategorie(@RequestBody Categorie c) {
        Categorie Categorie = CategorieService.add(c);
        return Categorie;
    }

    // http://localhost:8089/projet/Categorie/remove-Categorie/{Categorie-id}
    @DeleteMapping("/remove-Categorie/{Categorie-id}")
    public void removeCategorie(@PathVariable("Categorie-id") String id) {
        CategorieService.remove(id);
    }

    // http://localhost:8089/projet/Categorie/modify-Categorie
    @PutMapping("/modify-Categorie")
    public Categorie modifyCategorie(@RequestBody Categorie c) {
        Categorie Categorie = CategorieService.modify(c);
        return Categorie;
    }
}
