package com.beesidk.projet.controller;

import com.beesidk.projet.entity.Avis;
import com.beesidk.projet.entity.Reclamation;
import com.beesidk.projet.service.ReclamationService;
import com.beesidk.projet.service.aiService.SentimentAnalysisService;
import com.beesidk.projet.service.aiService.SpamDetectionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/fastapi")
public class FastController {

    private final SpamDetectionService fastApiService;
    private final SentimentAnalysisService sentimentAnalysisService;

    @PostMapping("/predictSpam")
    public Mono<Boolean> checkSpam(@RequestBody Reclamation reclamation) {
        return fastApiService.isSpam(reclamation);
    }

    @PostMapping("/predictSentiment")
    public Mono<String> checkSentiment(@RequestBody Avis avis) {
        return sentimentAnalysisService.predict_note(avis);
    }
}
