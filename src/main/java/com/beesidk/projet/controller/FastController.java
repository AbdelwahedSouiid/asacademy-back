package com.beesidk.projet.controller;

import com.beesidk.projet.entity.Reclamation;
import com.beesidk.projet.service.FastApiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/fastapi")
public class FastController {

    private final FastApiService fastApiService;

    @PostMapping("/check")
    public Mono<Boolean> checkSpam(@RequestBody Reclamation reclamation) {
        return fastApiService.isSpam(reclamation);
    }
}
