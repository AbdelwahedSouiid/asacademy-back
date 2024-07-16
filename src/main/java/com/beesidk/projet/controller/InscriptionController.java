package com.beesidk.projet.controller;


import com.beesidk.projet.entity.Inscription;
import com.beesidk.projet.entity.Inscription;
import com.beesidk.projet.service.IService;
import com.beesidk.projet.service.InscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/inscription")
public class InscriptionController {


    IService<Inscription> InscriptionService;

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
    public Inscription addInscription(@RequestBody Inscription c) {
        Inscription Inscription = InscriptionService.add(c);
        return Inscription;
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
}
