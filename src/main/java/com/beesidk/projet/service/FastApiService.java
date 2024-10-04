package com.beesidk.projet.service;

import com.beesidk.projet.entity.Reclamation;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class FastApiService {

    private WebClient.Builder webClientBuilder;

    public FastApiService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
        if (this.webClientBuilder == null) {
            throw new IllegalStateException("WebClient.Builder is not initialized");
        }
    }

    private final String FASTAPI_URL = "http://localhost:8000/predict";  // Mettre à jour l'URL selon votre configuration

    public Mono<Boolean> isSpam(Reclamation reclamation) {
        return webClientBuilder.build()
                .post()
                .uri(FASTAPI_URL)
                .bodyValue(reclamation)
                .retrieve()
                .bodyToMono(SpamResponse.class)  // Utiliser un DTO pour la réponse
                .map(SpamResponse::isSpam);  // Récupérer le champ "is_spam" de la réponse JSON
    }

    // DTO pour mapper la réponse de l'API FastAPI
    private static class SpamResponse {
        private boolean is_spam;

        @JsonProperty("is_spam")
        public boolean isSpam() {
            return is_spam;
        }

        public void setSpam(boolean is_spam) {
            this.is_spam = is_spam;
        }
    }
}
