package com.beesidk.projet.controller;


import com.beesidk.projet.entity.Formateur;
import com.beesidk.projet.interfaces.IService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/formateur")
public class FormateurController {


    IService<Formateur> FormateurService;


    // http://localhost:8081/projet/Formateur/retrieve-all-Formateurs
    @Operation(description = "Ce web service permet de récupérer toutes les Formateurs de la base de données")
    @GetMapping("/retrieve-all-Formateurs")
    public List<Formateur> getFormateurs() {
        List<Formateur> listFormateurs = FormateurService.retrieveAll();

        return listFormateurs;
    }

    // http://localhost:8081/projet/Formateur/retrieve-Formateur/8
    @GetMapping("/retrieve-Formateur/{Formateur-id}")
    public Formateur retrieveFormateur(@PathVariable("Formateur-id") String id) {
        Formateur Formateur = FormateurService.retrieve(id);
        return Formateur;
    }

    // http://localhost:8081/projet/Formateur/add-Formateur
    @PostMapping("/add-Formateur")
    public Formateur addFormateur(@RequestBody Formateur c) {
        Formateur Formateur = FormateurService.add(c);
        return Formateur;
    }

    // http://localhost:8081/projet/Formateur/remove-Formateur/{Formateur-id}
    @DeleteMapping("/remove-Formateur/{Formateur-id}")
    public void removeFormateur(@PathVariable("Formateur-id") String id) {
        FormateurService.remove(id);
    }

    // http://localhost:8081/projet/Formateur/modify-Formateur
    @PutMapping("/modify-Formateur")
    public Formateur modifyFormateur(@RequestBody Formateur c) {
        Formateur Formateur = FormateurService.modify(c);
        return Formateur;
    }
}
