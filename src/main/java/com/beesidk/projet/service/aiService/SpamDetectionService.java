package com.beesidk.projet.service.aiService;

import com.beesidk.projet.config.FastApiProperties;
import com.beesidk.projet.entity.Reclamation;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SpamDetectionService {

    private final WebClient.Builder webClientBuilder;
    private final FastApiProperties fastApiProperties;

    public SpamDetectionService(WebClient.Builder webClientBuilder, FastApiProperties fastApiProperties) {
        this.webClientBuilder = webClientBuilder;

        if (this.webClientBuilder == null) {
            throw new IllegalStateException("WebClient.Builder is not initialized");
        }
        this.fastApiProperties = fastApiProperties;
    }


    public Mono<Boolean> isSpam(Reclamation reclamation) {
        // Construire l'URL finale en ajoutant le préfixe
        String url = fastApiProperties.getBaseUrl() + "/spam/predict-spam"; // Ajoutez le préfixe spécifique

        return webClientBuilder.build()
                .post()
                .uri(url)
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
