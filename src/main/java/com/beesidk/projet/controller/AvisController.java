package com.beesidk.projet.controller;


import com.beesidk.projet.entity.Avis;


import com.beesidk.projet.service.AvisService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/avis")
public class AvisController {

    private AvisService avisService;

    // http://localhost:8089/projet/Avis/retrieve-all-Avis
    @Operation(description = "Ce web service permet de récupérer toutes les Aviss de la base de données")
    @GetMapping("/retrieve-all-Avis")
    public ResponseEntity<?> getAvis() {
        try {
            List<Avis> listAvis = avisService.retrieveAll();
            return ResponseEntity.ok(listAvis);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching avis: " + e.getMessage());
        }
    }

    // http://localhost:8089/projet/Avis/retrieve-Avis/8
    @GetMapping("/retrieve-Avis/{Avis-id}")
    public Avis retrieveAvis(@PathVariable("Avis-id") String id) {
        return avisService.retrieve(id);
    }

    // http://localhost:8089/projet/Avis/add-Avis
    @PostMapping("/add-Avis")
    public Avis addAvis(@RequestBody Avis avis) {

        return avisService.add(avis);
    }

    @DeleteMapping("/remove-Avis/{Avis-id}")
    public void removeAvis(@PathVariable("Avis-id") String id) {
        avisService.remove(id);
    }


    @PutMapping("/modify-Avis")
    public Avis modifyAvis(@RequestBody Avis avis) {
        return avisService.modify(avis);
    }

    @GetMapping("/retrieve-Avis-By-Cour/{cour-id}")
    public ResponseEntity<?> getAvis(@PathVariable("cour-id") String courId) {
        try {
            List<Avis> listAvis = avisService.getAvisByCour(courId);
            return ResponseEntity.ok(listAvis);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching avis: " + e.getMessage());
        }
    }

}
