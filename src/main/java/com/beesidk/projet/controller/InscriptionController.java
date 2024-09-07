package com.beesidk.projet.controller;


import com.beesidk.projet.entity.Inscription;
import com.beesidk.projet.interfaces.IService;
import com.beesidk.projet.interfaces.InterfaceInscriptionService;
import com.beesidk.projet.service.InscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/inscription")
public class InscriptionController {


    InterfaceInscriptionService InscriptionService;

    // http://localhost:8089/projet/Inscription/retrieve-all-Inscriptions
    @Operation(description = "Ce web service permet de récupérer toutes les Inscriptions de la base de données")
    @GetMapping("/retrieve-all-Inscriptions")
    public List<Inscription> getInscriptions() {
        List<Inscription> listInscriptions = InscriptionService.retrieveAll();
        return listInscriptions;
    }

    // http://localhost:8089/projet/Inscription/retrieve-Inscription/8
    @GetMapping("/retrieve-Inscription/{Inscription-id}")
    public Inscription retrieveInscription(@PathVariable("Inscription-id") String id) {
        Inscription Inscription = InscriptionService.retrieve(id);
        return Inscription;
    }

    // http://localhost:8089/projet/Inscription/add-Inscription
    @PostMapping("/add-Inscription")
    public Inscription addInscription(@RequestBody Inscription i) {
        return InscriptionService.add(i);
    }

    // http://localhost:8089/projet/Inscription/remove-Inscription/{Inscription-id}
    @DeleteMapping("/remove-Inscription/{Inscription-id}")
    public void removeInscription(@PathVariable("Inscription-id") String id) {
        InscriptionService.remove(id);
    }

    // http://localhost:8089/projet/Inscription/modify-Inscription
    @PutMapping("/modify-Inscription")
    public Inscription modifyInscription(@RequestBody Inscription c) {
        Inscription Inscription = InscriptionService.modify(c);
        return Inscription;
    }

    // http://localhost:8089/projet/Inscription/retrieve-all-Inscriptions
    @Operation(description = "Ce web service permet de récupérer toutes les Inscriptions By User de la base de données")
    @GetMapping("/retrieve-inscriptions-By-User/{email}")
    public List<Inscription> getInscriptionsByUser(@PathVariable("email") String email) {
        List<Inscription> listInscriptions = InscriptionService.getAllInscriptionByUser(email);
        return listInscriptions;
    }

    @GetMapping("/is_exist/{userId}/{courId}")
    public boolean isInscriptionExist(@PathVariable("userId") String USER_ID, @PathVariable("courId") String COUR_ID) {
        boolean isExist = InscriptionService.isInscriptionExist(USER_ID, COUR_ID);
        return isExist;
    }


}
