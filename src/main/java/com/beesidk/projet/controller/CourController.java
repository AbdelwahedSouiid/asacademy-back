package com.beesidk.projet.controller;


import com.beesidk.projet.entity.Cour;
import com.beesidk.projet.service.CourService;
import com.beesidk.projet.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cour")
public class CourController {


    IService<Cour> CourService;

    // http://localhost:8089/exam/Cour/retrieve-all-Cours
    @Operation(description = "Ce web service permet de récupérer toutes les Cours de la base de données")
    @GetMapping("/retrieve-all-Cours")
    public List<Cour> getCours() {
        List<Cour> listCours = CourService.retrieveAll();
        return listCours;
    }

    // http://localhost:8089/exam/Cour/retrieve-Cour/8
    @GetMapping("/retrieve-Cour/{Cour-id}")
    public Cour retrieveCour(@PathVariable("Cour-id") String id) {
        Cour Cour = CourService.retrieve(id);
        return Cour;
    }

    // http://localhost:8089/exam/Cour/add-Cour
    @PostMapping("/add-Cour")
    public Cour addCour(@RequestBody Cour c) {
        Cour Cour = CourService.add(c);
        return Cour;
    }

    // http://localhost:8089/exam/Cour/remove-Cour/{Cour-id}
    @DeleteMapping("/remove-Cour/{Cour-id}")
    public void removeCour(@PathVariable("Cour-id") String id) {
        CourService.remove(id);
    }

    // http://localhost:8089/exam/Cour/modify-Cour
    @PutMapping("/modify-Cour")
    public Cour modifyCour(@RequestBody Cour c) {
        Cour Cour = CourService.modify(c);
        return Cour;
    }
}
