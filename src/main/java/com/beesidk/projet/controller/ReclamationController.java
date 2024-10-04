package com.beesidk.projet.controller;


import com.beesidk.projet.entity.Reclamation;
import com.beesidk.projet.interfaces.IService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reclamation")
public class ReclamationController {


    IService<Reclamation> ReclamationService;
    
    @GetMapping("/retrieve-all-Reclamations")
    public List<Reclamation> getReclamations() {
        List<Reclamation> listReclamations = ReclamationService.retrieveAll();

        return listReclamations;
    }

    // http://localhost:8081/projet/Reclamation/retrieve-Reclamation/8
    @GetMapping("/retrieve-Reclamation/{Reclamation-id}")
    public Reclamation retrieveReclamation(@PathVariable("Reclamation-id") String id) {
        Reclamation Reclamation = ReclamationService.retrieve(id);
        return Reclamation;
    }

    // http://localhost:8081/projet/Reclamation/add-Reclamation
    @PostMapping("/add-Reclamation")
    public Reclamation addReclamation(@RequestBody Reclamation c) {
        Reclamation Reclamation = ReclamationService.add(c);
        return Reclamation;
    }

    // http://localhost:8081/projet/Reclamation/remove-Reclamation/{Reclamation-id}
    @DeleteMapping("/remove-Reclamation/{Reclamation-id}")
    public void removeReclamation(@PathVariable("Reclamation-id") String id) {
        ReclamationService.remove(id);
    }

    // http://localhost:8081/projet/Reclamation/modify-Reclamation
    @PutMapping("/modify-Reclamation")
    public Reclamation modifyReclamation(@RequestBody Reclamation c) {
        Reclamation Reclamation = ReclamationService.modify(c);
        return Reclamation;
    }
}
