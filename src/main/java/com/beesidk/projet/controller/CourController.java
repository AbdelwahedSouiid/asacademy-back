package com.beesidk.projet.controller;


import com.beesidk.projet.entity.Cour;
import com.beesidk.projet.entity.Tag;
import com.beesidk.projet.enums.PaiementType;
import com.beesidk.projet.interfaces.ICourService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cour")
public class CourController {


    ICourService CourService;

    // http://localhost:8089/exam/Cour/retrieve-all-Cours
    @Operation(description = "Ce web service permet de récupérer toutes les Cours de la base de données")
    @GetMapping("/retrieve-all-Cours")
    public ResponseEntity<?> getCours() {
        try {
            List<Cour> listCours = CourService.retrieveAll();
            return ResponseEntity.ok(listCours);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching cours: " + e.getMessage());
        }

    }

    // http://localhost:8089/exam/Cour/retrieve-Cour/8
    @GetMapping("/retrieve-Cour/{Cour-id}")
    public Cour retrieveCour(@PathVariable("Cour-id") String id) {
        Cour Cour = CourService.retrieve(id);
        return Cour;
    }


    // http://localhost:8089/exam/Cour/add-Cour
    @PostMapping("/add-Cour")
    public ResponseEntity<?> addCour(@RequestBody Cour cour) {
        try {
            List<Tag> tags = cour.getTags();
            System.out.println("voici les tags " + tags);
            Cour savedCour = CourService.add(cour);
            return ResponseEntity.ok(savedCour);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding cour: " + e.getMessage());
        }
    }

    // http://localhost:8089/exam/Cour/remove-Cour/{Cour-id}
    @DeleteMapping("/remove-Cour/{id}")
    public void removeCour(@PathVariable("id") String id) {
        CourService.remove(id);
    }

    // http://localhost:8089/exam/Cour/modify-Cour
    @PutMapping("/modify-Cour")
    public ResponseEntity<?> modifyCour(@RequestBody Cour c) {
        try {
            Cour cour = CourService.modify(c);
            return ResponseEntity.ok(cour);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating cour: " + e.getMessage());
        }
    }

    @GetMapping("/retrieve-By-Duree/{duree}")
    public List<Cour> searchByDuree(@PathVariable("duree") int duree) {
        List<Cour> cours = CourService.searchByDuration(duree);
        return cours;
    }

    @GetMapping("/retrieve-By-Price/{price}")
    public List<Cour> searchByPrice(@PathVariable("price") float price) {
        List<Cour> cours = CourService.searchByPrice(price);
        return cours;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Cour>> searchCour(
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "paiementType", required = false) PaiementType paiementType) {

        // Appel au service avec les filtres
        List<Cour> cours = CourService.search(tag, name, category, paiementType);
        return new ResponseEntity<>(cours, HttpStatus.OK);
    }


}
